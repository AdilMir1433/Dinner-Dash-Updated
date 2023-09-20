package com.example.rotiscnz.dtos.cartItemDTOs;

import com.example.rotiscnz.entities.CartItemEntity;

public class CartItemMapperDTO {
    public static CartItemEntity toCartItemEntityFromCartItemCreateDTO( CartItemCreateDTO cartItemCreateDTO){
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setCartByCartId(cartItemCreateDTO.getCartByCartId());
        cartItemEntity.setItemByItemId(cartItemCreateDTO.getItemByItemId());
        return cartItemEntity;
    }
}
