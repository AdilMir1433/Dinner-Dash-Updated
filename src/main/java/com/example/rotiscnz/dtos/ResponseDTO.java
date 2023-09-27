package com.example.rotiscnz.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseDTO<T> {
    private T data;
    private Integer ResponseCode;
    private String errorMessage;
    private String refreshToken;
}
