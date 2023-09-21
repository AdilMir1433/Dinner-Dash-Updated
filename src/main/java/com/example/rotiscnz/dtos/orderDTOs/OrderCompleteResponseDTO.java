package com.example.rotiscnz.dtos.orderDTOs;

import com.example.rotiscnz.dtos.ItemDTOs.ItemResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderCompleteResponseDTO {
    private Long id;
    private Timestamp orderTime;
    private String status;
    private Long cartID;
    private List<ItemResponseDTO> items;


}
