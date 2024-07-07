package com.springbootedigest.journal.App.controller;

import com.springbootedigest.journal.App.entity.User;
import com.springbootedigest.journal.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username) {
        User userData = userService.findByUserName(username);
        if (userData != null) {
            userData.setUsername(user.getUsername());
            userData.setPassword(user.getPassword());
            userService.saveEntry(userData);
        }

        return new ResponseEntity<>(userData, HttpStatus.NO_CONTENT);
    }

}
