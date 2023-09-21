package com.example.rotiscnz.security;

import com.example.rotiscnz.dtos.ModelToResponse;
import com.example.rotiscnz.dtos.userDTOs.UserResponseDTO;
import com.example.rotiscnz.entities.UserEntity;
import com.example.rotiscnz.mappers.UserMapper;
import com.example.rotiscnz.services.UserServiceImpl;
import com.example.rotiscnz.utility.SessionData;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
@Component
@RequiredArgsConstructor
@Slf4j
public class JWTAuthFilter extends OncePerRequestFilter {
    private final JWTUtility jwtHelper;
    private final UserServiceImpl userDetailsService;
    private final SessionData sessionData;
    private final UserMapper userMapper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null) {
            token = extractJwtToken(request);
            sessionData.setToken(token);
            String userId = null;
            try {
                userId = this.jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
                log.info(e.getMessage());
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
                log.info(e.getMessage());
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserEntity userDetails = this.userDetailsService.loadUserByUsername(userId);
                log.info("ROLE : "+userDetails.getRole());
                UserResponseDTO userResponseDTO = ModelToResponse.parseUserToResponse(userDetails);
                sessionData.setUser(userDetails);

                Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
                if (Boolean.TRUE.equals(validateToken)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    logger.info("Validation fails !!");
                }
            }
        }
        filterChain.doFilter(request, response);
    }
    private String extractJwtToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
    private static class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {
        private final String headerValue;
        public CustomHttpServletRequestWrapper(HttpServletRequest request, String headerValue) {
            super(request);
            this.headerValue = headerValue;
        }
        @Override
        public String getHeader(String name) {
            if ("Authorization".equalsIgnoreCase(name)) {
                return headerValue;
            }
            return super.getHeader(name);
        }
        @Override
        public Enumeration<String> getHeaderNames() {
            Enumeration<String> headerNames = super.getHeaderNames();
            if (headerNames == null) {
                headerNames = Collections.enumeration(Collections.singleton("Authorization"));
            }
            return headerNames;
        }
        @Override
        public Enumeration<String> getHeaders(String name) {
            if ("Authorization".equalsIgnoreCase(name)) {
                return Collections.enumeration(Collections.singleton(headerValue));
            }
            return super.getHeaders(name);
        }
    }
}