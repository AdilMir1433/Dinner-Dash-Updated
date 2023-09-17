package com.example.rotiscnz.dtos.userDTOs;

import com.example.rotiscnz.entities.CartEntity;
import com.example.rotiscnz.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String email;
    private String displayName;
    private String fullName;
    private Set<CartEntity> cartsById;
    private UserRole role;
    }

