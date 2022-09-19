package com.testproject.marketplace.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthenticateUserDTO {

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

}
