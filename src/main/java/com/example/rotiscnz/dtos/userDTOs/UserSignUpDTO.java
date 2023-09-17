package com.example.rotiscnz.dtos.userDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDTO {
    private String email;
    private String password;
    private String fullName;
    private String displayName;
}