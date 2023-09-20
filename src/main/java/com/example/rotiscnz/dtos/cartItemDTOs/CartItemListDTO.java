package com.example.rotiscnz.dtos.cartItemDTOs;

import com.example.rotiscnz.dtos.cartItemDTOs.CartItemCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemListDTO {
    List<CartItemCreateDTO> cartItemCreateDTO;
}
