package com.example.rotiscnz.dtos.cartDTOs;

import com.example.rotiscnz.entities.CartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CartMapperDTO {
    public static CartResponseDTO toCartResponseDTOFromCartEntity(CartEntity entity){
        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        cartResponseDTO.setId(entity.getId());
        cartResponseDTO.setUserByUserId(entity.getUserByUserId());
        return cartResponseDTO;
    }
}
