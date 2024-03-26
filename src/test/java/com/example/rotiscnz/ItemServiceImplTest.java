package com.example.rotiscnz;

import com.example.rotiscnz.dtos.ItemDTOs.ItemCreateDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemListResponseDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemResponseDTO;
import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.entities.CategoryEntity;
import com.example.rotiscnz.entities.ItemEntity;
import com.example.rotiscnz.repositories.CategoryRepository;
import com.example.rotiscnz.repositories.ItemRepository;
import com.example.rotiscnz.services.ItemServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

        // Call the service method
        ResponseDTO<ItemResponseDTO> responseDTO = itemService.getItem(itemId);

        // Assertions
        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getData());
        assertEquals(0, responseDTO.getResponseCode());

        // Verify interactions with mocked dependencies
        verify(itemRepository, times(1)).findById(itemId);
    }

    @Test
    void testUpdateItem() {
        // Prepare test data
        ItemCreateDTO itemCreateDTO = new ItemCreateDTO();
        itemCreateDTO.setCategoryID(Arrays.asList(1l,2L));
        itemCreateDTO.setItemPhoto("photo.jpg");
        itemCreateDTO.setTitle("Food");
        itemCreateDTO.setPrice(69);
        itemCreateDTO.setDescription("Yummy");

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemPhoto("photo.jpg");
        itemEntity.setTitle("Food");
        itemEntity.setPrice(69);
        itemEntity.setDescription("Yummy");

        when(itemRepository.findByTitle(anyString())).thenReturn(Optional.of(itemEntity));
        ResponseDTO<ItemResponseDTO> responseDTO = itemService.updateItem(itemCreateDTO);
        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getData());
        assertEquals(itemCreateDTO.getPrice(), itemEntity.getPrice());
    }
}
