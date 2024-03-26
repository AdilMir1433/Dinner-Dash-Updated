package com.example.rotiscnz.services;

import com.example.rotiscnz.dtos.ModelToResponse;
import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.userDTOs.UserResponseDTO;
import com.example.rotiscnz.dtos.userDTOs.UserSignUpDTO;
import com.example.rotiscnz.entities.UserEntity;
import com.example.rotiscnz.enums.UserRole;
import com.example.rotiscnz.repositories.UserRepository;
import com.example.rotiscnz.serviceinterfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseService implements UserServiceInterface {

    private final UserRepository userRepository;

    /**
     * Method to load user details by name
     *
     * @param username: username of the user to be loaded from the database (email) (String)
     * @return User: user details (User)
     */
    public UserEntity loadUserByUsername(String username){

        return userRepository
                .findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Method to login user into app
     *
     * @param email:    email of the user entered by user (String)
     * @param password: password entered by the user (String)
     * @return token: generated token is returned (String)
     */

    @Override
    public ResponseDTO<UserResponseDTO> login(String email, String password) {
        UserEntity user = loadUserByUsername(email);
        UserResponseDTO userResponseDTO = ModelToResponse.parseUserToResponse(user);

        if (!password.equals(user.getPassword())) {
            return generateFailureResponse("User Name or Password Error");
        }

        ResponseDTO<UserResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(userResponseDTO);
        responseDTO.setResponseCode(0);
        return responseDTO;

    }

    /**
     * Method to load user details by email and password
     *
     * @param email:    email of the user entered by user (String)
     * @param password: password entered by the user (String)
     * @return User: User details are returned (UserResponseDTO)
     */
    @Override
    public ResponseDTO<UserResponseDTO> getProfile(String email, String password) {
        UserEntity user = loadUserByUsername(email);
         if (!password.equals(user.getPassword())) {
            ResponseDTO<UserResponseDTO> responseDTO = new ResponseDTO<>();
            responseDTO.setResponseCode(-1);
            responseDTO.setErrorMessage("User Name or Password Error");
            return responseDTO;
        }
        ResponseDTO<UserResponseDTO> userResponseDTOResponseDTO = new ResponseDTO<>();

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setFullName(user.getFullName());
        userResponseDTO.setDisplayName(user.getDisplayName());
        userResponseDTOResponseDTO.setData(userResponseDTO);
        userResponseDTOResponseDTO.setResponseCode(0);
        return userResponseDTOResponseDTO;
    }

    /**
     * Method to save user to database
     *
     * @param user: User Data (UserResponseDTO)
     * @return User: User details are returned (UserResponseDTO)
     */

    @Override
    public ResponseDTO<UserResponseDTO> signUp(UserSignUpDTO user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setDisplayName(user.getDisplayName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setFullName(user.getFullName());
        userEntity.setRole(UserRole.CUSTOMER);
        UserEntity createdUser = userRepository.save(userEntity);
        ResponseDTO<UserResponseDTO> userResponseDTOResponseDTO = new ResponseDTO<>();
        UserResponseDTO userResponseDTO = ModelToResponse.parseUserToResponse(createdUser);
        userResponseDTOResponseDTO.setData(userResponseDTO);
        userResponseDTOResponseDTO.setResponseCode(0);
        return generateSuccessResponseForUser(userResponseDTO, "");

    }

    @Override
    public ResponseDTO<UserResponseDTO> adminSignUp(UserSignUpDTO user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setDisplayName(user.getDisplayName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setFullName(user.getFullName());
        userEntity.setRole(UserRole.ADMINISTRATOR);
        UserEntity createdUser = userRepository.save(userEntity);
        ResponseDTO<UserResponseDTO> userResponseDTOResponseDTO = new ResponseDTO<>();
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(createdUser.getId());
        userResponseDTO.setEmail(createdUser.getEmail());
        userResponseDTO.setRole(createdUser.getRole());
        userResponseDTO.setFullName(createdUser.getFullName());
        userResponseDTO.setDisplayName(createdUser.getDisplayName());
        userResponseDTOResponseDTO.setData(userResponseDTO);
        userResponseDTOResponseDTO.setResponseCode(0);
        return generateSuccessResponseForUser(userResponseDTO, "");

    }

}
