package com.uber.uberApp.services.impl;

import com.uber.uberApp.dto.DriverDTO;
import com.uber.uberApp.dto.SignUpDTO;
import com.uber.uberApp.dto.UserDTO;
import com.uber.uberApp.entities.User;
import com.uber.uberApp.entities.enums.Role;
import com.uber.uberApp.exceptions.RunTimeConflictException;
import com.uber.uberApp.repository.UserRepository;
import com.uber.uberApp.services.AuthService;
import com.uber.uberApp.services.RiderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;

    @Override
    public String login(String email, String password) {
        // TODO: Implement login logic
        return "";
    }

    @Override
    @Transactional
    public UserDTO signup(SignUpDTO signUpDTO) {
        userRepository.findByEmail(signUpDTO.getEmail()).ifPresent(existingUser -> {
            throw new RunTimeConflictException("User already exists", signUpDTO.getEmail());
        });

        try {
            User userEntity = modelMapper.map(signUpDTO, User.class);
            userEntity.setRoles(Set.of(Role.RIDER));
            User savedUser = userRepository.save(userEntity);
            riderService.createNewRider(savedUser);
            return modelMapper.map(savedUser, UserDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error during user registration", e);
        }
    }

    @Override
    public DriverDTO onBoardNewDriver(Long userId) {
        // TODO: Implement driver onboarding logic
        return null;
    }
}