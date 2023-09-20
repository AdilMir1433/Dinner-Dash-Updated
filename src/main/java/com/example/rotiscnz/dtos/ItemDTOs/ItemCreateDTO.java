package com.example.rotiscnz.dtos.ItemDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreateDTO {
    private String title;
    private String description;
    private String itemPhoto;
    private double price;
    private List<Long> categoryID;
}
