package com.example.rotiscnz;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryCreateDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryResponseDTO;
import com.example.rotiscnz.entities.CategoryEntity;
import com.example.rotiscnz.mappers.CategoryMapper;
import com.example.rotiscnz.repositories.CategoryRepository;
import com.example.rotiscnz.services.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Spy
    private CategoryRepository categoryRepository;

    @Spy
    private CategoryMapper categoryMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCategory() {

        CategoryCreateDTO createDTO = new CategoryCreateDTO();
        CategoryEntity categoryEntity = new CategoryEntity();
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        when(categoryMapper.toCategoryEntityFromCategoryCreateDTO(createDTO)).thenReturn(categoryEntity);
        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);

        ResponseDTO<CategoryResponseDTO> responseDTO = categoryService.createCategory(createDTO);

        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getData());
        assertEquals(0, responseDTO.getResponseCode());

        verify(categoryMapper, times(1)).toCategoryEntityFromCategoryCreateDTO(createDTO);
        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    public void testGetCategories() {

        List<CategoryEntity> categoryEntities = new ArrayList<>();
        CategoryEntity categoryEntity1 = new CategoryEntity();
        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntities.add(categoryEntity1);
        categoryEntities.add(categoryEntity2);

        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        CategoryResponseDTO categoryResponseDTO1 = new CategoryResponseDTO();
        CategoryResponseDTO categoryResponseDTO2 = new CategoryResponseDTO();
        categoryResponseDTOS.add(categoryResponseDTO1);
        categoryResponseDTOS.add(categoryResponseDTO2);

        when(categoryRepository.findAll()).thenReturn(categoryEntities);

        ResponseDTO<List<CategoryResponseDTO>> responseDTO = categoryService.getCategories();

        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getData());
        assertEquals(0, responseDTO.getResponseCode());
        assertEquals(2, responseDTO.getData().size());

        verify(categoryRepository, times(1)).findAll();
    }
}
