package com.example.rotiscnz.serviceinterfaces;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryCreateDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryResponseDTO;

import java.util.List;

public interface CategoryServiceInterface {
    ResponseDTO<CategoryResponseDTO> createCategory(CategoryCreateDTO createDTO);
    ResponseDTO<List<CategoryResponseDTO>> getCategories();
}
