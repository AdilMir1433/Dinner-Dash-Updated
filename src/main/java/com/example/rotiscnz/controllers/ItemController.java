package com.example.rotiscnz.controllers;

import com.example.rotiscnz.dtos.ItemDTOs.ItemCreateDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemListResponseDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemResponseDTO;
import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ItemController {
    private final ItemService service;

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
