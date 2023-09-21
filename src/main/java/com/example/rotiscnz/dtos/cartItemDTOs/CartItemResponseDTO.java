package com.example.rotiscnz.dtos.cartItemDTOs;

import com.example.rotiscnz.entities.CartEntity;
import com.example.rotiscnz.entities.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemResponseDTO {
    private Long id;
    private Long cartByCartId;
    private Long itemByItemId;
    private Long orderID;

}
