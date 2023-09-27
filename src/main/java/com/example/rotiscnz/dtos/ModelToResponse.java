package com.example.rotiscnz.dtos;

import com.example.rotiscnz.dtos.userDTOs.UserResponseDTO;
import com.example.rotiscnz.entities.UserEntity;

public class ModelToResponse {

    private ModelToResponse(){

    }

    public static UserResponseDTO parseUserToResponse(UserEntity user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setFullName(user.getFullName());
        userResponseDTO.setDisplayName(user.getFullName());
        return userResponseDTO;
    }
}
