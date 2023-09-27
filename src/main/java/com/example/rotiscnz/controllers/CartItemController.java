package com.example.rotiscnz.controllers;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemCreateDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemResponseDTO;
import com.example.rotiscnz.services.CartItemServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cart_item")
@CrossOrigin("http://localhost:3000")
//@CrossOrigin({"https://d100-122-129-66-106.ngrok-free.app", "*"})
public class CartItemController {
    private final CartItemServiceImpl service;
    @PostMapping("/add-cart-item")
    public ResponseDTO<CartItemResponseDTO> addItemToCart(@RequestBody CartItemCreateDTO cartItemCreateDTO){
        log.info("Received Request Body: " + cartItemCreateDTO);
        return service.addItemToCart(cartItemCreateDTO);
    }

    @GetMapping("/get-cart-items/{id}")
    public ResponseDTO<List<CartItemResponseDTO>> getAllItems(@PathVariable Long id)
    {
        return service.getAllItems(id);
    }
}
