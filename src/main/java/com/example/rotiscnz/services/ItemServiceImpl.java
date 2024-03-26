package com.example.rotiscnz.services;

import com.example.rotiscnz.dtos.ItemDTOs.ItemCreateDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemListResponseDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemMapperDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemResponseDTO;
import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryListDTO;
import com.example.rotiscnz.entities.CategoryEntity;
import com.example.rotiscnz.entities.ItemEntity;
import com.example.rotiscnz.repositories.CategoryRepository;
import com.example.rotiscnz.repositories.ItemRepository;
import com.example.rotiscnz.serviceinterfaces.ItemServiceInterface;
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
public class ItemServiceImpl extends BaseService implements ItemServiceInterface {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public ResponseDTO<ItemResponseDTO> save(ItemCreateDTO item) {
        try {
            List<CategoryEntity> categoryEntities = categoryRepository.findAllById(item.getCategoryID());
            List<Long> categoryIdsList = categoryEntities.stream().map(CategoryEntity::getId).toList();
            CategoryListDTO categoryListDTO = new CategoryListDTO(categoryIdsList);
            ItemEntity itemEntity = ItemMapperDTO.toItemEntityFromItemCreateDTO(item);
            itemEntity.setCategoryID(objectMapper.writeValueAsString(categoryListDTO));
            itemRepository.save(itemEntity);
            ItemResponseDTO itemResponseDTO = ItemMapperDTO.toItemResponseDTOFromItemEntity(itemEntity);
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

    @Override
    public ResponseDTO<ItemListResponseDTO> getAllItems() {
        try {
            List<ItemEntity> items = itemRepository.findAll();
            List<ItemResponseDTO> itemResponseDTOS = new ArrayList<>();

            for (ItemEntity item : items) {
                ItemResponseDTO itemResponseDTO =ItemMapperDTO.toItemResponseDTOFromItemEntity(item);
                if (item.getCategoryID() != null) {
                    CategoryListDTO categoryIdList = objectMapper.readValue(item.getCategoryID(), CategoryListDTO.class);
                    List<CategoryEntity> categoryEntities = categoryRepository.findAllById(categoryIdList.getCategoryListId());
                    itemResponseDTO.setCategoryID(categoryEntities.stream().map(CategoryEntity::getCategoryName).toList());
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

    @Override
    public ResponseDTO<ItemResponseDTO> getItem(Long id){
        Optional<ItemEntity> item =  itemRepository.findById(id);
        ResponseDTO<ItemResponseDTO> responseDTO= new ResponseDTO<>();
        if(item.isPresent()){
            ItemResponseDTO itemResponseDTO = ItemMapperDTO.toItemResponseDTOFromItemEntity(item.get());
            responseDTO.setData(itemResponseDTO);
            responseDTO.setResponseCode(0);
            return responseDTO;
        }
        responseDTO.setResponseCode(-1);
        responseDTO.setErrorMessage("Item Not Found");
        return responseDTO;
    }

    @Override
    public ResponseDTO<ItemResponseDTO> updateItem(ItemCreateDTO itemCreateDTO) {
        Optional<ItemEntity> item =  itemRepository.findByTitle(itemCreateDTO.getTitle());
        ResponseDTO<ItemResponseDTO> responseDTO= new ResponseDTO<>();
        if(item.isPresent()){
            ItemEntity itemEntity = item.get();
            itemEntity.setItemPhoto(itemCreateDTO.getItemPhoto());
            itemEntity.setTitle(itemCreateDTO.getTitle());
            itemEntity.setDescription(itemCreateDTO.getDescription());
            ItemResponseDTO itemResponseDTO = ItemMapperDTO.toItemResponseDTOFromItemEntity(itemEntity);
            responseDTO.setData(itemResponseDTO);
            responseDTO.setResponseCode(0);
            return responseDTO;
        }
        responseDTO.setResponseCode(-1);
        responseDTO.setErrorMessage("Item Not Found");
        return responseDTO;
    }


}
