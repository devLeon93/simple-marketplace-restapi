package com.testproject.marketplace.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SuccessAuthenticateResponseDTO {

    private Long id;
    private String email;
    private String username;
    private List<String> roles;


    public SuccessAuthenticateResponseDTO(Long id,
                                          String email,
                                          String username,
                                          List<String> roles
                              ){
        this.id = id;
        this.email = email;
        this.username = username;
        this.roles = roles;

    }

}
