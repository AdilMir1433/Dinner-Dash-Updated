package com.example.rotiscnz.utility;

import com.example.rotiscnz.dtos.userDTOs.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
//@SessionScope
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class SessionData implements Serializable {

    private UserResponseDTO user;
    private String token;
    public void removeSessionData(){
        this.user = null;
    }
}


