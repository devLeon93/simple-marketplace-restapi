package com.testproject.marketplace.service;

import com.testproject.marketplace.dto.AuthenticateUserDTO;
import com.testproject.marketplace.dto.RegisterUserDTO;
import com.testproject.marketplace.dto.SuccessResponseDTO;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

public interface UserService {

    void createUser(RegisterUserDTO registerUser);

    SuccessResponseDTO authenticateUser(AuthenticateUserDTO authenticateUser);


}
