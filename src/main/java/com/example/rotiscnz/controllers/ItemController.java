package com.example.rotiscnz.controllers;

import com.example.rotiscnz.dtos.ItemDTOs.ItemCreateDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemListResponseDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemResponseDTO;
import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.services.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
//@CrossOrigin("http://localhost:3000")
@CrossOrigin({"https://d100-122-129-66-106.ngrok-free.app", "*"})
public class ItemController {
    private final ItemServiceImpl service;

    @PostMapping("/save")
    public ResponseDTO<ItemResponseDTO> save(@RequestBody ItemCreateDTO item){
        return service.save(item);
    }

    @GetMapping("/get-items")
    public ResponseDTO<ItemListResponseDTO> getAllItems(){
        return service.getAllItems();
    }

    @GetMapping("/get-item/{id}")
    public ResponseDTO<ItemResponseDTO> getItem(@PathVariable Long id){
        return service.getItem(id);
    }
}
