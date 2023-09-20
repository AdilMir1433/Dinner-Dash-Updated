package com.example.rotiscnz.services;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryCreateDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryMapperDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryResponseDTO;
import com.example.rotiscnz.entities.CategoryEntity;
import com.example.rotiscnz.mappers.CategoryMapper;
import com.example.rotiscnz.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService extends BaseService{
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public ResponseDTO<CategoryResponseDTO> createCategory(CategoryCreateDTO createDTO){
        CategoryEntity categoryEntity = mapper.toCategoryEntityFromCategoryCreateDTO(createDTO);
        repository.save(categoryEntity);
        CategoryResponseDTO categoryResponseDTO = CategoryMapperDTO.toCategoryResponseDTOFromCategoryEntity(categoryEntity);
        ResponseDTO<CategoryResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setResponseCode(0);
        responseDTO.setData(categoryResponseDTO);
        return responseDTO;
    }
    public ResponseDTO<List<CategoryResponseDTO>> getCategories(){
        List<CategoryEntity> categoryEntities = repository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        for(CategoryEntity categoryEntity: categoryEntities){
            CategoryResponseDTO categoryResponseDTO = CategoryMapperDTO.toCategoryResponseDTOFromCategoryEntity(categoryEntity);
            categoryResponseDTOS.add(categoryResponseDTO);
        }
        ResponseDTO<List<CategoryResponseDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(categoryResponseDTOS);
        responseDTO.setResponseCode(0);
        return responseDTO;
    }
}
