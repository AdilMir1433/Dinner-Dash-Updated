package com.example.rotiscnz;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemCreateDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemResponseDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderCreateDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderResponseDTO;
import com.example.rotiscnz.entities.CartItemEntity;
import com.example.rotiscnz.entities.OrderEntity;
import com.example.rotiscnz.enums.OrderType;
import com.example.rotiscnz.repositories.CartItemRepository;
import com.example.rotiscnz.repositories.OrderRepository;
import com.example.rotiscnz.security.JWTUtility;
import com.example.rotiscnz.services.CartItemServiceImpl;
import com.example.rotiscnz.services.OrderServiceImpl;
import com.example.rotiscnz.utility.SessionData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartItemServiceImplTest {

    @InjectMocks
    private CartItemServiceImpl cartItemService;

    @Spy
    private CartItemRepository cartItemRepository;

    @Spy
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @Spy
    private SessionData sessionData;

    @Spy
    private JWTUtility jwtUtility;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllItems() {
        // Prepare test data
        Long cartId = 1L;
        CartItemEntity cartItemEntity1 = new CartItemEntity();
        cartItemEntity1.setCartByCartId(cartId);
        CartItemEntity cartItemEntity2 = new CartItemEntity();
        cartItemEntity2.setCartByCartId(cartId + 1); // Different cart ID
        List<CartItemEntity> cartItemEntities = new ArrayList<>();
        cartItemEntities.add(cartItemEntity1);
        cartItemEntities.add(cartItemEntity2);
        sessionData.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZGlsLm1pckBkZXZzaW5jLmNvbSIsImlhdCI6MTY5NTc5NTk1OSwiZXhwIjoxNjk1ODgyMzU5fQ.nHC1i29vmwTy2EIiv8OwqIagilUwkRbA09DD4fL_TF2VG87Lk76qHwb4qPI171y_Ofoe5dr57mOLyC4yUCl2Ng");

        when(cartItemRepository.findAll()).thenReturn(cartItemEntities);

        // Call the service method
        ResponseDTO<List<CartItemResponseDTO>> responseDTO = cartItemService.getAllItems(cartId);

        // Assertions
        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getData());
        assertEquals(0, responseDTO.getResponseCode());
        assertEquals(1, responseDTO.getData().size()); // Only one item for the specified cart ID

        // Verify interactions with mocked dependencies
        verify(cartItemRepository, times(1)).findAll();
    }
}
