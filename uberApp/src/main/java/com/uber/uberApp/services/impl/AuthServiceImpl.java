package com.uber.uberApp.services.impl;

import com.uber.uberApp.dto.DriverDTO;
import com.uber.uberApp.dto.SignUpDTO;
import com.uber.uberApp.dto.UserDTO;
import com.uber.uberApp.services.AuthService;

/**
 * Implementation of the AuthService interface.
 * This class handles authentication-related operations such as login, signup, and driver onboarding.
 */
public class AuthServiceImpl implements AuthService {

    @Override
    public String login(String email, String password) {
        // TODO: Implement login logic
        return "";
    }

    @Override
    public UserDTO signup(SignUpDTO signUpDTO) {
        // TODO: Implement signup logic
        return null;
    }

    @Override
    public DriverDTO onBoardNewDriver(Long userId) {
        // TODO: Implement driver onboarding logic
        return null;
    }
}