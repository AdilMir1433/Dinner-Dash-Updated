package com.example.rotiscnz.mappers;

import com.example.rotiscnz.dtos.categoryDTOs.CategoryCreateDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryResponseDTO;
import com.example.rotiscnz.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    CategoryEntity toCategoryEntityFromCategoryCreateDTO(CategoryCreateDTO createDTO);

    CategoryResponseDTO toCategoryResponseDTOFromCategoryEntity(CategoryEntity categoryEntity);
}
