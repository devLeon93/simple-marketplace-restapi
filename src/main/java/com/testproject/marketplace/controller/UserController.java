package com.testproject.marketplace.controller;

import com.testproject.marketplace.dto.user.AuthenticateUserDTO;
import com.testproject.marketplace.dto.user.RegisterUserDTO;
import com.testproject.marketplace.service.UserService;
import com.testproject.marketplace.util.ExceptionResponse;
import com.testproject.marketplace.util.JWTSuccessAuthenticateResponse;
import com.testproject.marketplace.util.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Registrate User")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @Validated @RequestBody RegisterUserDTO register) {
        userService.createUser(register);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Operation(summary = "Authenticate User")
    @PostMapping("/signin")
    public ResponseEntity<JWTSuccessAuthenticateResponse> authenticateUser(
            @Validated @RequestBody AuthenticateUserDTO authenticate) {
        return new ResponseEntity<>(userService.authenticateUser(authenticate), HttpStatus.OK);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> customException(BadCredentialsException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("BAD_REQUEST");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
