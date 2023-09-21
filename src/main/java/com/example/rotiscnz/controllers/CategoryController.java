package com.example.rotiscnz.controllers;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryCreateDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryResponseDTO;
import com.example.rotiscnz.services.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
//@CrossOrigin("http://localhost:3000")
@CrossOrigin({"https://d100-122-129-66-106.ngrok-free.app", "*"})
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;
    @PostMapping("/create")
    public ResponseDTO<CategoryResponseDTO> createCategory(@RequestBody CategoryCreateDTO category){
        return categoryServiceImpl.createCategory(category);
    }
    @GetMapping("/get-categories")
    public ResponseDTO<List<CategoryResponseDTO>> getCategories(){
        return categoryServiceImpl.getCategories();
    }
}
