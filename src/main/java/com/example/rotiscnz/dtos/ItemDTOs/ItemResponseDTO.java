package com.example.rotiscnz.dtos.ItemDTOs;

import com.example.rotiscnz.entities.CartItemEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO {
    private int id;
    private String title;
    private String description;
    private String itemPhoto;
    private double price;
    private Set<CartItemEntity> cartItemsById;
}
