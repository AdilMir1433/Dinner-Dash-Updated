package com.example.rotiscnz.dtos.cartItemDTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemResponseDTO {
    private Long id;
    private Long cartByCartId;
    private Long itemByItemId;
    private Long orderID;

}
