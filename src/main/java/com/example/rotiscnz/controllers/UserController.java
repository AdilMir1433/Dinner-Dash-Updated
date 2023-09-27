package com.example.rotiscnz.controllers;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.userDTOs.UserLoginDTO;
import com.example.rotiscnz.dtos.userDTOs.UserResponseDTO;
import com.example.rotiscnz.dtos.userDTOs.UserSignUpDTO;
import com.example.rotiscnz.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
//@CrossOrigin({"https://d100-122-129-66-106.ngrok-free.app", "*"})
public class UserController {
    private final UserServiceImpl userService;

    /**
     * Method to login user
     * @return VOID
     */

    @PostMapping("/login")
    public ResponseDTO<UserResponseDTO> login(@RequestBody UserLoginDTO user){
        return userService.login(user.getEmail(),user.getPassword());
    }
    /**
     * Method to creat user account
     * @return UserResponseDTO
     */
    @PostMapping("/signup")
    public ResponseDTO<UserResponseDTO> signUp(@RequestBody UserSignUpDTO userSignUpDTO){
        return userService.signUp(userSignUpDTO);
    }

}
