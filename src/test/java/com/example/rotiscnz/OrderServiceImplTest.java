package com.example.rotiscnz;

import com.example.rotiscnz.enums.UserRole;
import com.example.rotiscnz.services.OrderServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.rotiscnz.dtos.orderDTOs.*;
import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.entities.*;
import com.example.rotiscnz.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;
    @Spy
    private OrderRepository orderRepository;
    @Spy
    private CartItemRepository cartItemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOrder() {
        // Prepare test data
        OrderCreateDTO orderCreateDTO = new OrderCreateDTO();
        // Populate orderCreateDTO with necessary data

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFullName("Adil Mir");
        userEntity.setRole(UserRole.CUSTOMER);
        userEntity.setEmail("adil.mir@devsinc.com");
        userEntity.setPassword("password");
        userEntity.setDisplayName("adilmir");

        // Mock the behavior of repositories and save methods
        when(orderRepository.save(any(OrderEntity.class))).thenAnswer(invocation -> {
            OrderEntity orderEntity = invocation.getArgument(0);
            orderEntity.setId(1L); // Simulate saving to the database
            return orderEntity;
        });

        // Call the service method
        ResponseDTO<OrderResponseDTO> responseDTO = orderService.saveOrder(orderCreateDTO);

        // Assertions
        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getData());

        // Verify interactions with mocked dependencies
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    void testGetOrderOfUser() {

        Long userId = 1L;

        OrderEntity orderEntity = new OrderEntity();
        List<OrderEntity> orderEntities = Arrays.asList(orderEntity);

        when(orderRepository.findAllByCartID(userId)).thenReturn(orderEntities);
        when(cartItemRepository.findByOrderID(orderEntity.getId())).thenReturn(new ArrayList<>());

        ResponseDTO<List<OrderCompleteResponseDTO>> responseDTO = orderService.getOrderOfUser(userId);

        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getData());
        assertFalse(responseDTO.getData().isEmpty());

        verify(orderRepository, times(1)).findAllByCartID(userId);
        verify(cartItemRepository, times(1)).findByOrderID(orderEntity.getId());
    }

    @Test
    void testGetAllOrders() {
        OrderEntity orderEntity = new OrderEntity();
        List<OrderEntity> orderEntities = Arrays.asList(orderEntity);

        when(orderRepository.findAll()).thenReturn(orderEntities);

        ResponseDTO<List<OrderResponseDTO>> responseDTO = orderService.getAllOrders();

        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getData());
        assertFalse(responseDTO.getData().isEmpty());

        verify(orderRepository, times(1)).findAll();
    }
}
