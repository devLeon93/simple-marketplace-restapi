package com.testproject.marketplace.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JWTSuccessAuthenticateResponse {

    private Long id;
    private String email;
    private String username;
    private List<String> roles;
    private String token;


    public JWTSuccessAuthenticateResponse(Long id,
                                          String email,
                                          String username,
                                          List<String> roles,
                                          String token) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.roles = roles;
        this.token = token;
    }
}
