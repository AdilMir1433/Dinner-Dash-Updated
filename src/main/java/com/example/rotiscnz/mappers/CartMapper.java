package com.example.rotiscnz.mappers;

import com.example.rotiscnz.dtos.cartDTOs.CartResponseDTO;
import com.example.rotiscnz.entities.CartEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartResponseDTO toCartResponseDTOFromCartEntity(CartEntity cart);
}
