package com.example.rotiscnz.dtos.categoryDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {
    private Long id;
    private String categoryName;
    private String categoryPhoto;
}
