package com.example.rotiscnz.serviceinterfaces;

import com.example.rotiscnz.dtos.ItemDTOs.ItemCreateDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemListResponseDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemResponseDTO;
import com.example.rotiscnz.dtos.ResponseDTO;

public interface ItemServiceInterface {
    ResponseDTO<ItemResponseDTO> save(ItemCreateDTO item);
    ResponseDTO<ItemListResponseDTO> getAllItems();
    ResponseDTO<ItemResponseDTO> getItem(Long id);

    ResponseDTO<ItemResponseDTO> updateItem(ItemCreateDTO itemCreateDTO);
}
