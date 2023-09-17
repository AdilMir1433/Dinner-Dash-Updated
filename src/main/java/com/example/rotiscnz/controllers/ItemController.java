package com.example.rotiscnz.controllers;

import com.example.rotiscnz.dtos.ItemDTOs.ItemCreateDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemResponseDTO;
import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}
