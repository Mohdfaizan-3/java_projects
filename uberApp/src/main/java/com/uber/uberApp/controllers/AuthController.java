package com.uber.uberApp.controllers;

import com.uber.uberApp.dto.SignUpDTO;
import com.uber.uberApp.dto.UserDTO;
import com.uber.uberApp.services.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody SignUpDTO signUpDTO) {
        return authService.signup(signUpDTO);

    }
}
