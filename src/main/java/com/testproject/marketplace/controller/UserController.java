package com.testproject.marketplace.controller;

import com.testproject.marketplace.dto.AuthenticateUserDTO;
import com.testproject.marketplace.dto.RegisterUserDTO;
import com.testproject.marketplace.dto.SuccessResponseDTO;
import com.testproject.marketplace.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Validated @RequestBody RegisterUserDTO register) {
        userService.createUser(register);
        return new  ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<SuccessResponseDTO> authenticateUser(@Validated @RequestBody AuthenticateUserDTO authenticate) {
        return new ResponseEntity<>(userService.authenticateUser(authenticate), HttpStatus.OK);
    }
}
