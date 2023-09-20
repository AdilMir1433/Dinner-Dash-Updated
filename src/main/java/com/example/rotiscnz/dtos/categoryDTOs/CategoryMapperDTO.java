package com.example.rotiscnz.dtos.categoryDTOs;

import com.example.rotiscnz.entities.CategoryEntity;

public class CategoryMapperDTO {
    public static CategoryResponseDTO toCategoryResponseDTOFromCategoryEntity(CategoryEntity categoryEntity){
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(categoryEntity.getId());
        categoryResponseDTO.setCategoryName(categoryEntity.getCategoryName());
        categoryResponseDTO.setCategoryPhoto(categoryEntity.getCategoryPhoto());
        return categoryResponseDTO;
    }
}
