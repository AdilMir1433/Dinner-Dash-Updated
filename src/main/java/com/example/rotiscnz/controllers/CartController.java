package com.example.rotiscnz.controllers;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.cartDTOs.CartCreateDTO;
import com.example.rotiscnz.dtos.cartDTOs.CartResponseDTO;
import com.example.rotiscnz.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class CartController {
    private final CartService service;
    @PostMapping("/create-cart/{id}")
    public ResponseDTO<CartResponseDTO> createCart(@PathVariable Long id)
    {
        return service.createCart(id);
    }
}
