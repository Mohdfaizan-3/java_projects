package com.springbootedigest.journal.App.controller;

import com.springbootedigest.journal.App.entity.User;
import com.springbootedigest.journal.App.service.UserDetailsServiceImpl;
import com.springbootedigest.journal.App.service.UserService;
import com.springbootedigest.journal.App.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PublicController {

    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public void signup(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
      try {
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
          userDetailsServiceImpl.loadUserByUsername(user.getUsername());
          String jwt = jwtUtils.generateToken(user.getUsername());
          return new ResponseEntity<>(jwt, HttpStatus.OK);
      } catch (Exception e) {

      }
        return null;
    }

//    @PostMapping("/create-user")
//    public ResponseEntity<?> createUser(@RequestBody User user) {
//        try {
//            userService.saveNewUser(user);
//            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating user: " + e.getMessage());
//        }
//    }

}
