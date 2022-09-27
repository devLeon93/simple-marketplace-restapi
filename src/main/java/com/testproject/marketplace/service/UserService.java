package com.testproject.marketplace.service;

import com.testproject.marketplace.dto.user.AuthenticateUserDTO;
import com.testproject.marketplace.dto.user.RegisterUserDTO;
import com.testproject.marketplace.util.JWTSuccessAuthenticateResponse;

public interface UserService {

    void createUser(RegisterUserDTO registerUser);

    JWTSuccessAuthenticateResponse authenticateUser(AuthenticateUserDTO authenticateUser);
}
