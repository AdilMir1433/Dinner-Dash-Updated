package com.example.rotiscnz.dtos.orderDTOs;

import com.example.rotiscnz.entities.OrderEntity;

public class OrderMapperDTO {

    public static OrderEntity toOrderEntityFromOrderCreateDTO(OrderCreateDTO orderCreateDTO){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderTime(orderCreateDTO.getOrderTime());
        orderEntity.setStatus(orderCreateDTO.getStatus());
        orderEntity.setCartID(orderCreateDTO.getCartID());
        return orderEntity;
    }
    public static OrderResponseDTO toOrderResponseDTOFromOrderEntity(OrderEntity orderEntity){
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderTime(orderEntity.getOrderTime());
        orderResponseDTO.setStatus(orderEntity.getStatus());
        orderResponseDTO.setCartID(orderEntity.getCartID());
        orderResponseDTO.setId(orderEntity.getId());
        return orderResponseDTO;
    }
}
