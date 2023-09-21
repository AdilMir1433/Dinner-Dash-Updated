package com.example.rotiscnz.dtos.cartItemDTOs;

import com.example.rotiscnz.entities.CartItemEntity;

public class CartItemMapperDTO {
    public static CartItemEntity toCartItemEntityFromCartItemCreateDTO( CartItemCreateDTO cartItemCreateDTO){
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setCartByCartId(cartItemCreateDTO.getCartByCartId());
        cartItemEntity.setItemByItemId(cartItemCreateDTO.getItemByItemId());
        return cartItemEntity;
    }
    public static CartItemResponseDTO toCartItemResponseDTOFromCartItemEntity(CartItemEntity cartItem)
    {
        CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();
        cartItemResponseDTO.setCartByCartId(cartItem.getCartByCartId());
        cartItemResponseDTO.setId(cartItem.getId());
        cartItemResponseDTO.setItemByItemId(cartItem.getItemByItemId());
        cartItemResponseDTO.setOrderID(cartItem.getOrderID());
        return cartItemResponseDTO;
    }
}
