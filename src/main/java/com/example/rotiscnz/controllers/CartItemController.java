package com.example.rotiscnz.controllers;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemCreateDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemListDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemResponseDTO;
import com.example.rotiscnz.services.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart_item")
@CrossOrigin("http://localhost:3000")
public class CartItemController {
    private final CartItemService service;
    @PostMapping("/add-cart-item")
    public ResponseDTO<CartItemResponseDTO> addItemToCart(@RequestBody CartItemCreateDTO cartItemCreateDTO){
        return service.addItemToCart(cartItemCreateDTO);
    }

    @GetMapping("/get-cart-items/{id}")
    public ResponseDTO<List<CartItemResponseDTO>> getAllItems(@PathVariable Long id)
    {
        return service.getAllItems(id);
    }
}
