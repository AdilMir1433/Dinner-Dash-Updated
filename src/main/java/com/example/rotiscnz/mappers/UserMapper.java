package com.example.rotiscnz.mappers;

import com.example.rotiscnz.dtos.userDTOs.UserLoginDTO;
import com.example.rotiscnz.dtos.userDTOs.UserResponseDTO;
import com.example.rotiscnz.dtos.userDTOs.UserSignUpDTO;
import com.example.rotiscnz.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toUserResponseDTOFromUserEntity(UserEntity user);
    UserSignUpDTO toUserSignUpDTO(UserEntity user);
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "password", source = "user.password")
    UserLoginDTO toUserLoginDTO(UserEntity user);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    UserEntity toUserEntityFromUserSignUpDTO(UserSignUpDTO user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserResponseDTO toUserResponseDTOFromUserSignUpDTO(UserSignUpDTO userSignUpDTO);

}
