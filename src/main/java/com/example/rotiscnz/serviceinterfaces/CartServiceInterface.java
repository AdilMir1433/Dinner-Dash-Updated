package com.example.rotiscnz.serviceinterfaces;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.cartDTOs.CartResponseDTO;

public interface CartServiceInterface {
    ResponseDTO<CartResponseDTO> createCart(Long id);
}
