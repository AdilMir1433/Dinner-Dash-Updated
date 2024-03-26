package com.example.rotiscnz.serviceinterfaces;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.userDTOs.UserResponseDTO;
import com.example.rotiscnz.dtos.userDTOs.UserSignUpDTO;

public interface UserServiceInterface {
    ResponseDTO<UserResponseDTO> login(String email, String password);
    ResponseDTO<UserResponseDTO> getProfile(String email, String password);
    ResponseDTO<UserResponseDTO> signUp(UserSignUpDTO user);
    ResponseDTO<UserResponseDTO> adminSignUp(UserSignUpDTO user);
}
