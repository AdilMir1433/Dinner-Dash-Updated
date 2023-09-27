package com.example.rotiscnz;

import com.example.rotiscnz.dtos.ItemDTOs.ItemCreateDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemListResponseDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemResponseDTO;
import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryListDTO;
import com.example.rotiscnz.entities.CategoryEntity;
import com.example.rotiscnz.entities.ItemEntity;
import com.example.rotiscnz.repositories.CategoryRepository;
import com.example.rotiscnz.repositories.ItemRepository;
import com.example.rotiscnz.security.JWTUtility;
import com.example.rotiscnz.services.ItemServiceImpl;
import com.example.rotiscnz.utility.SessionData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Spy
    private ItemRepository itemRepository;

    @Spy
    private CategoryRepository categoryRepository;

    @Spy
    private ObjectMapper objectMapper;

    @Spy
    private SessionData sessionData;

    @Spy
    private JWTUtility jwtUtility;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveItem() throws JsonProcessingException {
        // Prepare test data
        ItemCreateDTO itemCreateDTO = new ItemCreateDTO();
        itemCreateDTO.setCategoryID(Arrays.asList(1l,2L));
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);

        sessionData.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZGlsLm1pckBkZXZzaW5jLmNvbSIsImlhdCI6MTY5NTc5NTk1OSwiZXhwIjoxNjk1ODgyMzU5fQ.nHC1i29vmwTy2EIiv8OwqIagilUwkRbA09DD4fL_TF2VG87Lk76qHwb4qPI171y_Ofoe5dr57mOLyC4yUCl2Ng");
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(categoryEntity);

        when(categoryRepository.findAllById(itemCreateDTO.getCategoryID())).thenReturn(categoryEntities);
        ResponseDTO<ItemResponseDTO> responseDTO = itemService.save(itemCreateDTO);
        verify(categoryRepository, times(1)).findAllById(anyList());
    }

    @Test
    void testGetAllItems() throws JsonProcessingException {

        ItemEntity itemEntity = new ItemEntity();

        List<ItemEntity> itemEntities = new ArrayList<>();
        itemEntities.add(itemEntity);

        ItemCreateDTO itemCreateDTO = new ItemCreateDTO();
        itemCreateDTO.setCategoryID(Arrays.asList(1l,2L));

        when(itemRepository.findAll()).thenReturn(itemEntities);
        sessionData.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZGlsLm1pckBkZXZzaW5jLmNvbSIsImlhdCI6MTY5NTc5NTk1OSwiZXhwIjoxNjk1ODgyMzU5fQ.nHC1i29vmwTy2EIiv8OwqIagilUwkRbA09DD4fL_TF2VG87Lk76qHwb4qPI171y_Ofoe5dr57mOLyC4yUCl2Ng");
        ResponseDTO<ItemListResponseDTO> responseDTO = itemService.getAllItems();

        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getData());
        assertEquals(0, responseDTO.getResponseCode());
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void testGetItem() {
        // Prepare test data
        Long itemId = 1L;

        ItemEntity itemEntity = new ItemEntity();
        // Populate itemEntity with necessary data

        when(itemRepository.findById(itemId)).thenReturn(Optional.of(itemEntity));
        sessionData.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZGlsLm1pckBkZXZzaW5jLmNvbSIsImlhdCI6MTY5NTc5NTk1OSwiZXhwIjoxNjk1ODgyMzU5fQ.nHC1i29vmwTy2EIiv8OwqIagilUwkRbA09DD4fL_TF2VG87Lk76qHwb4qPI171y_Ofoe5dr57mOLyC4yUCl2Ng");

        // Call the service method
        ResponseDTO<ItemResponseDTO> responseDTO = itemService.getItem(itemId);

        // Assertions
        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getData());
        assertEquals(0, responseDTO.getResponseCode());

        // Verify interactions with mocked dependencies
        verify(itemRepository, times(1)).findById(itemId);
    }
}
