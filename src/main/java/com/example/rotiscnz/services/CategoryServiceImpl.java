package com.example.rotiscnz.services;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryCreateDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryMapperDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryResponseDTO;
import com.example.rotiscnz.entities.CategoryEntity;
import com.example.rotiscnz.mappers.CategoryMapper;
import com.example.rotiscnz.repositories.CategoryRepository;
import com.example.rotiscnz.security.JWTUtility;
import com.example.rotiscnz.serviceinterfaces.CategoryServiceInterface;
import com.example.rotiscnz.utility.SessionData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends BaseService implements CategoryServiceInterface {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;
    private final SessionData sessionData;
    private final JWTUtility utility;

    @Override
    public ResponseDTO<CategoryResponseDTO> createCategory(CategoryCreateDTO createDTO){
        CategoryEntity categoryEntity = mapper.toCategoryEntityFromCategoryCreateDTO(createDTO);
        repository.save(categoryEntity);
        CategoryResponseDTO categoryResponseDTO = CategoryMapperDTO.toCategoryResponseDTOFromCategoryEntity(categoryEntity);
        ResponseDTO<CategoryResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setResponseCode(0);
        responseDTO.setData(categoryResponseDTO);
        if (sessionData!=null && !sessionData.getToken().isEmpty() && Boolean.TRUE.equals(utility.isTokenExpired(sessionData.getToken()))) {
            responseDTO.setRefreshToken(utility.generateToken(sessionData.getUser()));
        }
        return responseDTO;
    }
    @Override
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
        if (sessionData!=null && !sessionData.getToken().isEmpty() && Boolean.TRUE.equals(utility.isTokenExpired(sessionData.getToken()))) {
            responseDTO.setRefreshToken(utility.generateToken(sessionData.getUser()));
        }
        return responseDTO;
    }
}
