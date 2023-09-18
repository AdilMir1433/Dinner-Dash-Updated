package com.example.rotiscnz.controllers;

import com.example.rotiscnz.dtos.ItemDTOs.ItemResponseDTO;
import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryCreateDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryResponseDTO;
import com.example.rotiscnz.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/create")
    public ResponseDTO<CategoryResponseDTO> createCategory(@RequestBody CategoryCreateDTO category){
        return categoryService.createCategory(category);
    }
    @GetMapping("/get-categories")
    public ResponseDTO<List<CategoryResponseDTO>> getCategories(){
        return categoryService.getCategories();
    }
}
