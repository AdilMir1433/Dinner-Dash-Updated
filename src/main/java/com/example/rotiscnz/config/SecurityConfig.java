package com.example.rotiscnz.config;

import com.example.rotiscnz.enums.UserRole;
import com.example.rotiscnz.security.JWTAuthEntry;
import com.example.rotiscnz.security.JWTAuthFilter;
import com.example.rotiscnz.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserService userService;
    private final JWTAuthEntry point;
    private final JWTAuthFilter filter;
    private final PasswordEncoder passwordEncoder;
    private static final String[] AUTH_WHITELIST = {
            "/user/login",
            "/user/signup",
            "/api-docs",
            "/swagger-ui/**",
            "/swagger-resources/*",
    };
    private static final String[] AUTHORITY_LIST = {
            "/items/save",
            "/category/create",
    };
    private static final String[] ALLOWED_ALL = {
            "/items/get-items",
            "/category/get-categories",
            "items/get-item/**",
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeRequests()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .requestMatchers(AUTHORITY_LIST).hasAuthority("ADMINISTRATOR")
                .requestMatchers(ALLOWED_ALL).permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}







