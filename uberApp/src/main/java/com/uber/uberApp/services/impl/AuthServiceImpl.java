package com.uber.uberApp.services.impl;

import com.uber.uberApp.dto.DriverDTO;
import com.uber.uberApp.dto.SignUpDTO;
import com.uber.uberApp.dto.UserDTO;
import com.uber.uberApp.services.AuthService;

public class AuthServiceImpl implements AuthService {
    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDTO signup(SignUpDTO signUpDTO) {
        return null;
    }

    @Override
    public DriverDTO onBoardNewDriver(Long userId) {
        return null;
    }
}
