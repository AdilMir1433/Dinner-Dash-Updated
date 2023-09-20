package com.example.rotiscnz.dtos.ItemDTOs;

import com.example.rotiscnz.entities.ItemEntity;

public class ItemMapperDTO {
    public static ItemEntity toItemEntityFromItemCreateDTO(ItemCreateDTO item){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemPhoto(item.getItemPhoto());
        itemEntity.setTitle(item.getTitle());
        itemEntity.setPrice(item.getPrice());
        itemEntity.setDescription(item.getDescription());
        return itemEntity;
    }

    public static ItemResponseDTO toItemResponseDTOFromItemEntity(ItemEntity item)
    {
        ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
        itemResponseDTO.setId(item.getId());
        itemResponseDTO.setDescription(item.getDescription());
        itemResponseDTO.setItemPhoto(item.getItemPhoto());
        itemResponseDTO.setTitle(item.getTitle());
        itemResponseDTO.setPrice(item.getPrice());
        return itemResponseDTO;
    }
}
