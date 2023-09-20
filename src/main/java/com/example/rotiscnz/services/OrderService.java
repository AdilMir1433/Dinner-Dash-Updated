package com.example.rotiscnz.services;

import com.example.rotiscnz.dtos.orderDTOs.OrderCreateDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderMapperDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderResponseDTO;
import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.entities.OrderEntity;
import com.example.rotiscnz.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public ResponseDTO<OrderResponseDTO> saveOrder(OrderCreateDTO orderCreateDTO){
        OrderEntity orderEntity = OrderMapperDTO.toOrderEntityFromOrderCreateDTO(orderCreateDTO);
        repository.save(orderEntity);
        OrderResponseDTO orderResponseDTO = OrderMapperDTO.toOrderResponseDTOFromOrderEntity(orderEntity);
        ResponseDTO<OrderResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(orderResponseDTO);
        return responseDTO;
    }

}
