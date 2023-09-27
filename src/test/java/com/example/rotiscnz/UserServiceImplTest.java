package com.example.rotiscnz;

import com.example.rotiscnz.dtos.userDTOs.UserResponseDTO;
import com.example.rotiscnz.dtos.userDTOs.UserSignUpDTO;
import com.example.rotiscnz.entities.UserEntity;
import com.example.rotiscnz.enums.UserRole;
import com.example.rotiscnz.mappers.UserMapper;
import com.example.rotiscnz.repositories.UserRepository;
import com.example.rotiscnz.security.JWTUtility;
import com.example.rotiscnz.services.UserServiceImpl;
import com.example.rotiscnz.utility.SessionData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Spy
    private UserRepository userRepository;

    @Spy
    private PasswordEncoder passwordEncoder;

    @Spy
    private JWTUtility jwtUtility;

    @Spy
    private UserMapper userMapper;

    @Spy
    private SessionData sessionData;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testLoadUserByUsername() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("adil.mir@devsinc.com");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));

        assertDoesNotThrow(() -> userService.loadUserByUsername("adil.mir@devsinc.com"));

        verify(userRepository, times(1)).findByEmail("adil.mir@devsinc.com");
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nonexistent@example.com"));

        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
    }

    @Test
    void testLogin() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("adil.mir@devsinc.com");
        userEntity.setPassword("heheboi12");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtUtility.generateToken(userEntity)).thenReturn("test_token");

        UserResponseDTO expectedUserResponseDTO = new UserResponseDTO();
        expectedUserResponseDTO.setEmail("adil.mir@devsinc.com");

        var responseDTO = userService.login("adil.mir@devsinc.com", "password");

        assertNotNull(responseDTO);
        assertEquals(0, responseDTO.getResponseCode());
        assertEquals("test_token", responseDTO.getRefreshToken());
        assertEquals(expectedUserResponseDTO, responseDTO.getData());
    }

    @Test
    void testLogin_InvalidPassword() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("adil.mir@devsinc.com");
        userEntity.setPassword("heheboi12");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        var responseDTO = userService.login("adil.mir@devsinc.com", "devSingh");

        assertNotNull(responseDTO);
        assertEquals(-1, responseDTO.getResponseCode());
        assertEquals("User Name or Password Error", responseDTO.getErrorMessage());
        assertNull(responseDTO.getRefreshToken());
        assertNull(responseDTO.getData());
    }
    @Test
    void testSignUp() {
        // Prepare test data
        UserSignUpDTO userSignUpDTO = new UserSignUpDTO();
        userSignUpDTO.setFullName("Adil Mir");
        userSignUpDTO.setEmail("adil.mir@devsinc.com");
        userSignUpDTO.setPassword("password");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFullName("Adil Mir");
        userEntity.setEmail("adil.mir@devsinc.com");
        userEntity.setPassword("hashed_password");
        userEntity.setRole(UserRole.CUSTOMER);

        UserResponseDTO expectedUserResponseDTO = new UserResponseDTO();
        expectedUserResponseDTO.setId(1L);
        expectedUserResponseDTO.setFullName("Adil Mir");
        expectedUserResponseDTO.setEmail("adil.mir@devsinc.com");
        expectedUserResponseDTO.setRole(UserRole.CUSTOMER);
        expectedUserResponseDTO.setDisplayName("Adil Mir");

        // Mock the behavior of dependencies
        when(passwordEncoder.encode(anyString())).thenReturn("hashed_password");
        when(userMapper.toUserEntityFromUserSignUpDTO(userSignUpDTO)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(jwtUtility.generateToken(userEntity)).thenReturn("test_token");
        sessionData.setUser(userEntity);

        // Call the signUp method
        var responseDTO = userService.signUp(userSignUpDTO);

        // Assertions
        assertNotNull(responseDTO);
        assertEquals(0, responseDTO.getResponseCode());
        assertEquals("test_token", responseDTO.getRefreshToken());
        assertNotNull(responseDTO.getData());
        assertEquals(expectedUserResponseDTO, responseDTO.getData());

        // Verify interactions with mocked dependencies
        verify(passwordEncoder, times(1)).encode("password");
        verify(userMapper, times(1)).toUserEntityFromUserSignUpDTO(userSignUpDTO);
        verify(userRepository, times(1)).save(userEntity);
        verify(jwtUtility, times(1)).generateToken(userEntity);
    }

}
