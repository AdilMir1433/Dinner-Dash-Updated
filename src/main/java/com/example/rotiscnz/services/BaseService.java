package com.example.rotiscnz.services;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.userDTOs.UserResponseDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseService {

    /**
     * Method to creat success response
     * @return VOID
     */
    public static ResponseDTO<Void> generateSuccessResponse() {
        ResponseDTO<Void> responseDTO = new ResponseDTO<>();
        responseDTO.setResponseCode(0);
        responseDTO.setData(null);
        responseDTO.setErrorMessage(null);
        return responseDTO;
    }
    /**
     * Method to creat failure response
     * @return VOID
     */
    public static ResponseDTO<UserResponseDTO> generateFailureResponse(String errorMessage) {
        ResponseDTO<UserResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setResponseCode(-1);
        responseDTO.setData(null);
        responseDTO.setErrorMessage(errorMessage);
        return responseDTO;
    }

    /**
     * Method to creat success for user SignUp
     * @param userResponseDTO: User info
     * @return responseDTO
     */
    public static ResponseDTO<UserResponseDTO> generateSuccessResponseForUser(UserResponseDTO userResponseDTO,String token) {
        ResponseDTO<UserResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(userResponseDTO);
        log.info("ResponseDTO : "+responseDTO);
        responseDTO.setResponseCode(0);
        responseDTO.setRefreshToken(token);
        return responseDTO;
    }

    /**
     * Method to creat success response for User Login
     * @param token: Generated Token: String
     * @return ResponseDTO
     */
    public static ResponseDTO<UserResponseDTO> generateSuccessResponseForLogin(String token,UserResponseDTO userResponseDTO) {
        ResponseDTO<UserResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setResponseCode(0);
        responseDTO.setData(userResponseDTO);
        responseDTO.setRefreshToken(token);
        responseDTO.setErrorMessage(null);
        return responseDTO;
    }

}
