package com.example.rotiscnz.dtos.categoryDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListDTO {
    private List<Integer> categoryListId;
}
