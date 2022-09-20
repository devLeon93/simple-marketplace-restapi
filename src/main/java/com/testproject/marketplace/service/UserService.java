package com.testproject.marketplace.service;

import com.testproject.marketplace.dto.AuthenticateUserDTO;
import com.testproject.marketplace.dto.RegisterUserDTO;
import com.testproject.marketplace.dto.SuccessAuthenticateResponseDTO;

public interface UserService {

    void createUser(RegisterUserDTO registerUser);

    SuccessAuthenticateResponseDTO authenticateUser(AuthenticateUserDTO authenticateUser);


}
