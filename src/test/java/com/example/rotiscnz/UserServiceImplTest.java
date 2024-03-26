package com.example.rotiscnz;

import com.example.rotiscnz.dtos.ModelToResponse;
import com.example.rotiscnz.dtos.userDTOs.UserResponseDTO;
import com.example.rotiscnz.dtos.userDTOs.UserSignUpDTO;
import com.example.rotiscnz.entities.UserEntity;
import com.example.rotiscnz.enums.UserRole;
import com.example.rotiscnz.mappers.UserMapper;
import com.example.rotiscnz.repositories.UserRepository;
import com.example.rotiscnz.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Spy
    private UserRepository userRepository;

    @Spy
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testLoadUserByUsername() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@dummy.com");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));

        assertDoesNotThrow(() -> userService.loadUserByUsername("test@dummy.com"));

        verify(userRepository, times(1)).findByEmail("test@dummy.com");
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.loadUserByUsername("nonexistent@example.com"));

        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
    }

    @Test
    void testLogin() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@dummy.com");
        userEntity.setPassword("heheboi12");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));

        UserResponseDTO expectedUserResponseDTO = new UserResponseDTO();
        expectedUserResponseDTO.setEmail("test@dummy.com");

        var responseDTO = userService.login("test@dummy.com", "heheboi12");

        assertNotNull(responseDTO);
        assertEquals(0, responseDTO.getResponseCode());
        assertEquals(expectedUserResponseDTO, responseDTO.getData());
    }

    @Test
    void testLogin_InvalidPassword() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@dummy.com");
        userEntity.setPassword("heheboi12");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));

        var responseDTO = userService.login("test@dummy.com", "devSingh");

        assertNotNull(responseDTO);
        assertEquals(-1, responseDTO.getResponseCode());
        assertEquals("User Name or Password Error", responseDTO.getErrorMessage());
        assertNull(responseDTO.getData());
    }
    @Test
    void testSignUp() {
        // Prepare test data
        UserSignUpDTO userSignUpDTO = new UserSignUpDTO();
        userSignUpDTO.setFullName("Usman Iftikhar");
        userSignUpDTO.setEmail("test@dummy.com");
        userSignUpDTO.setPassword("password");
        userSignUpDTO.setDisplayName("uz69");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFullName("Usman Iftikhar");
        userEntity.setEmail("test@dummy.com");
        userEntity.setPassword("password");
        userEntity.setDisplayName("uz69");
        userEntity.setRole(UserRole.CUSTOMER);

        UserResponseDTO expectedUserResponseDTO = new UserResponseDTO();
        expectedUserResponseDTO.setId(1L);
        expectedUserResponseDTO.setFullName("Usman Iftikhar");
        expectedUserResponseDTO.setEmail("test@dummy.com");
        expectedUserResponseDTO.setRole(UserRole.CUSTOMER);
        expectedUserResponseDTO.setDisplayName("Usman Iftikhar");

        // Mock the behavior of dependencies
        when(userRepository.save(any())).thenReturn(userEntity);
        // Call the signUp method
        var responseDTO = userService.signUp(userSignUpDTO);

        // Assertions
        assertNotNull(responseDTO);
        assertEquals(0, responseDTO.getResponseCode());
        assertNotNull(responseDTO.getData());
        assertEquals(expectedUserResponseDTO, responseDTO.getData());

    }

}
