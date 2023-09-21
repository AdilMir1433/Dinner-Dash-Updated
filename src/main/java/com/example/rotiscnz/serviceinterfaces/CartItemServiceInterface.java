package com.example.rotiscnz.serviceinterfaces;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemCreateDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemResponseDTO;

import java.util.List;

public interface CartItemServiceInterface {
    ResponseDTO<CartItemResponseDTO> addItemToCart(CartItemCreateDTO cartItemCreateDTO);
    ResponseDTO<List<CartItemResponseDTO>> getAllItems(Long id);
}
