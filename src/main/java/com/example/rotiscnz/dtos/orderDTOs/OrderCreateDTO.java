package com.example.rotiscnz.dtos.orderDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderCreateDTO {
    private Timestamp orderTime;
    private String status;
    private Long cartID;
}
