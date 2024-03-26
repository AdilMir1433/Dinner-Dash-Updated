package com.example.rotiscnz;


import com.example.rotiscnz.dtos.userDTOs.UserSignUpDTO;
import com.example.rotiscnz.repositories.UserRepository;
import com.example.rotiscnz.serviceinterfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class RotiScnzApplication implements CommandLineRunner {
    private final UserServiceInterface userService;
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(RotiScnzApplication.class, args);
    }

    @Override
    public void run(String... args){
        if (userRepository.count() == 0) {
            UserSignUpDTO userSignUpDTO = new UserSignUpDTO();
            userSignUpDTO.setPassword("8080");
            userSignUpDTO.setEmail("mir43316@gmail.com");
            userSignUpDTO.setFullName("Admin User");
            userSignUpDTO.setDisplayName("admin69");
            userService.adminSignUp(userSignUpDTO);
            log.info("Admin Created");
        }
    }
}
