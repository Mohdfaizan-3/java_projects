package com.uber.uberApp.services;

import com.uber.uberApp.dto.DriverDTO;
import com.uber.uberApp.dto.SignUpDTO;
import com.uber.uberApp.dto.UserDTO;

public interface AuthService {

    String login(String email, String password);

    UserDTO signup(SignUpDTO signUpDTO);

    DriverDTO onBoardNewDriver(Long userId);
}
