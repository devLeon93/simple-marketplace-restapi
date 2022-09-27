package com.testproject.marketplace.dto.user;

import com.testproject.marketplace.model.URole;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RegisterUserDTO {

    private URole userRole;

    @NotEmpty(message = "Please enter your username")
    private String username;

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;

    @NotEmpty(message = "Confirm your password please")
    private String confirmPassword;

    
}
