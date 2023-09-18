package com.example.rotiscnz.dtos.ItemDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemListResponseDTO {

    private List<ItemResponseDTO> itemResponseDTOS;
}
