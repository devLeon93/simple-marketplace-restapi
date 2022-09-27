package com.testproject.marketplace.service;

import com.testproject.marketplace.dto.user.AuthenticateUserDTO;
import com.testproject.marketplace.dto.user.RegisterUserDTO;
import com.testproject.marketplace.exception.UserExistException;
import com.testproject.marketplace.model.User;
import com.testproject.marketplace.repository.UserRepository;
import com.testproject.marketplace.security.JWT.JWTTokenProvider;
import com.testproject.marketplace.security.UserCustomDetail;
import com.testproject.marketplace.util.JWTSuccessAuthenticateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTTokenProvider jwtTokenProvider;

    public static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public void createUser(RegisterUserDTO registerUser) {
        Optional<User> userByUsername = userRepository.findUserByUsername(registerUser.getUsername());
        if(userByUsername.isPresent()){
            throw new UserExistException(
                    "The user " + registerUser.getUsername() + " already exist. Please check credentials");

        }
        User user = new User();
        user.setRole(registerUser.getUserRole());
        user.setUsername(registerUser.getUsername());
        user.setEmail(registerUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.getPassword()));
        LOG.info("Creating User {}", registerUser.getUsername());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public JWTSuccessAuthenticateResponse authenticateUser(AuthenticateUserDTO authenticateUser) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticateUser.getUsername(), authenticateUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        UserCustomDetail userDetails = (UserCustomDetail) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JWTSuccessAuthenticateResponse(
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getUsername(),
                roles,
                jwt
        );
    }
}
