package com.example.rotiscnz;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.cartDTOs.CartResponseDTO;
import com.example.rotiscnz.entities.CartEntity;
import com.example.rotiscnz.repositories.CartRepository;
import com.example.rotiscnz.security.JWTUtility;
import com.example.rotiscnz.services.CartServiceImpl;
import com.example.rotiscnz.utility.SessionData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Spy
    private CartRepository cartRepository;

    @Spy
    private SessionData sessionData;

    @Spy
    private JWTUtility jwtUtility;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCart_NewCart() {
        // Prepare test data
        Long userId = 1L;
        CartEntity cartEntity = new CartEntity();
        when(cartRepository.findAll()).thenReturn(new ArrayList<>());
        when(cartRepository.save(any())).thenReturn(cartEntity);
        sessionData.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZGlsLm1pckBkZXZzaW5jLmNvbSIsImlhdCI6MTY5NTc5NTk1OSwiZXhwIjoxNjk1ODgyMzU5fQ.nHC1i29vmwTy2EIiv8OwqIagilUwkRbA09DD4fL_TF2VG87Lk76qHwb4qPI171y_Ofoe5dr57mOLyC4yUCl2Ng");

        ResponseDTO<CartResponseDTO> responseDTO = cartService.createCart(userId);

        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getData());
        assertEquals(0, responseDTO.getResponseCode());

        verify(cartRepository, times(1)).findAll();
        verify(cartRepository, times(1)).save(any(CartEntity.class));
    }

    @Test
    public void testCreateCart_ExistingCart() {
        // Prepare test data
        Long userId = 1L;
        CartEntity cartEntity = new CartEntity();
        cartEntity.setUserByUserId(userId);
        List<CartEntity> cartEntities = new ArrayList<>();
        cartEntities.add(cartEntity);

        when(cartRepository.findAll()).thenReturn(cartEntities);

        ResponseDTO<CartResponseDTO> responseDTO = cartService.createCart(userId);

        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getData());
        assertEquals(0, responseDTO.getResponseCode());

        verify(cartRepository, times(1)).findAll();
    }
}
