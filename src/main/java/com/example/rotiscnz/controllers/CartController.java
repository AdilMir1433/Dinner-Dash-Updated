package com.example.rotiscnz.controllers;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.cartDTOs.CartResponseDTO;
import com.example.rotiscnz.services.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
//@CrossOrigin("http://localhost:3000")
@CrossOrigin({"https://d100-122-129-66-106.ngrok-free.app", "*"})
public class CartController {
    private final CartServiceImpl service;
    @PostMapping("/create-cart/{id}")
    public ResponseDTO<CartResponseDTO> createCart(@PathVariable Long id)
    {
        return service.createCart(id);
    }
}
