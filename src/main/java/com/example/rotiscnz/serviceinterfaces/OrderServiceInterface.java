package com.example.rotiscnz.serviceinterfaces;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderCompleteResponseDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderCreateDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderResponseDTO;

import java.util.List;

public interface OrderServiceInterface {
    ResponseDTO<OrderResponseDTO> saveOrder(OrderCreateDTO orderCreateDTO);
    ResponseDTO<List<OrderCompleteResponseDTO>> getOrderOfUser(Long id);
    ResponseDTO<List<OrderResponseDTO>> getAllOrders();
}
