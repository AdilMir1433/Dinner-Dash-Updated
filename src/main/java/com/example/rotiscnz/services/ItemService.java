package com.example.rotiscnz.services;

import com.example.rotiscnz.dtos.ItemDTOs.ItemCreateDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemResponseDTO;
import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.entities.ItemEntity;
import com.example.rotiscnz.mappers.ItemMapper;
import com.example.rotiscnz.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService extends BaseService{
    private final ItemRepository itemRepository;
    private final ItemMapper mapper;

    public ResponseDTO<ItemResponseDTO> save(ItemCreateDTO item){
        ItemEntity itemEntity = mapper.toItemEntityFromItemCreateDTO(item);
        itemRepository.save(itemEntity);
        ItemResponseDTO itemResponseDTO = mapper.toItemResponseDTOFromItemEntity(itemEntity);
        ResponseDTO<ItemResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(itemResponseDTO);
        responseDTO.setResponseCode(0);
        return responseDTO;
    }


}
