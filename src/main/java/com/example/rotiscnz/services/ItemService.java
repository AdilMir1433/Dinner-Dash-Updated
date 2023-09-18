package com.example.rotiscnz.services;

import com.example.rotiscnz.dtos.ItemDTOs.ItemCreateDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemListResponseDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemResponseDTO;
import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryListDTO;
import com.example.rotiscnz.entities.CategoryEntity;
import com.example.rotiscnz.entities.ItemEntity;
import com.example.rotiscnz.mappers.ItemMapper;
import com.example.rotiscnz.repositories.CategoryRepository;
import com.example.rotiscnz.repositories.ItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService extends BaseService {
    private final ItemRepository itemRepository;
    private final ItemMapper mapper;
    private final CategoryRepository categoryRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public ResponseDTO<ItemResponseDTO> save(ItemCreateDTO item) {
        try {
            List<CategoryEntity> categoryEntities = categoryRepository.findAllByIdIn(item.getCategoryID());
            List<Integer> categoryIdsList = categoryEntities.stream().map(CategoryEntity::getId).toList();
            CategoryListDTO categoryListDTO = new CategoryListDTO(categoryIdsList);
            ItemEntity itemEntity = mapper.toItemEntityFromItemCreateDTO(item);
            itemEntity.setCategoryID(objectMapper.writeValueAsString(categoryListDTO));
            itemRepository.save(itemEntity);
            ItemResponseDTO itemResponseDTO = mapper.toItemResponseDTOFromItemEntity(itemEntity);
            ResponseDTO<ItemResponseDTO> responseDTO = new ResponseDTO<>();
            responseDTO.setData(itemResponseDTO);
            responseDTO.setResponseCode(0);
            return responseDTO;
        } catch (JsonProcessingException ex) {
            ResponseDTO<ItemResponseDTO> responseDTO = new ResponseDTO<>();
            responseDTO.setData(null);
            responseDTO.setErrorMessage("Error saving item");
            responseDTO.setResponseCode(-1);
            return responseDTO;
        }
    }

    public ResponseDTO<ItemListResponseDTO> getAllItems() {
        try {
            List<ItemEntity> items = itemRepository.findAll();
            List<ItemResponseDTO> itemResponseDTOS = new ArrayList<>();

            for (ItemEntity item : items) {
                ItemResponseDTO itemResponseDTO = mapper.toItemResponseDTOFromItemEntity(item);
                if (item.getCategoryID() != null) {
                    CategoryListDTO categoryIdList = objectMapper.readValue(item.getCategoryID(), CategoryListDTO.class);
                    List<CategoryEntity> categoryEntities = categoryRepository.findAllByIdIn(categoryIdList.getCategoryListId());
                    itemResponseDTO.setCategoryID(categoryEntities.stream().map(categoryEntity -> {
                        return categoryEntity.getCategoryName();
                    }).toList());
                }
                itemResponseDTOS.add(itemResponseDTO);
            }
            ResponseDTO<ItemListResponseDTO> responseDTO = new ResponseDTO<>();
            ItemListResponseDTO itemListResponseDTO = new ItemListResponseDTO(itemResponseDTOS);
            responseDTO.setData(itemListResponseDTO);
            responseDTO.setResponseCode(0);
            return responseDTO;
        } catch (JsonProcessingException ex) {
            ResponseDTO<ItemListResponseDTO> responseDTO = new ResponseDTO<>();
            responseDTO.setData(null);
            responseDTO.setResponseCode(-1);
            return responseDTO;
        }
    }

    public ResponseDTO<ItemResponseDTO> getItem(Long id){
        Optional<ItemEntity> item =  itemRepository.findById(id);
        ResponseDTO<ItemResponseDTO> responseDTO= new ResponseDTO<>();
        if(item.isPresent()){
            ItemResponseDTO itemResponseDTO = mapper.toItemResponseDTOFromItemEntity(item.get());
            responseDTO.setData(itemResponseDTO);
            responseDTO.setResponseCode(0);
            return responseDTO;
        }
        responseDTO.setResponseCode(-1);
        responseDTO.setErrorMessage("Item Not Found");
        return responseDTO;
    }

}
