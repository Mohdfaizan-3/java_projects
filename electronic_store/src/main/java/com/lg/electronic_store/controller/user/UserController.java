package com.lg.electronic_store.controller.user;

import com.lg.electronic_store.dao.user.UserRequest;
import com.lg.electronic_store.service.user.UserService;
import com.lg.electronic_store.utils.apiResponse.ApiResponse;
import com.lg.electronic_store.utils.apiResponse.PagableResponseHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserRequest> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserRequest userRequest1 = userService.create(userRequest);
        return new ResponseEntity<>(userRequest1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRequest> updateUser(@Valid @RequestBody UserRequest userRequest, @PathVariable("id") String id) {
        UserRequest userRequest1 = userService.update(userRequest, id);
        return new ResponseEntity<>(userRequest1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id) {
        userService.delete(id);
        ApiResponse userDeletedSuccessfully = ApiResponse.builder()
                .message("user deleted successfully")
                .success(true).status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(userDeletedSuccessfully, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRequest> getUser(@PathVariable String id) {
        UserRequest userRequest = userService.getUser(id);
        return new ResponseEntity<>(userRequest, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<PagableResponseHelper<UserRequest>> getAllUsers(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                                          @RequestParam(value = "size", defaultValue = "5", required = false) int size,
                                                                          @RequestParam(value = "sortBy", defaultValue = "username", required = false) String sortBy,
                                                                          @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        PagableResponseHelper<UserRequest> users = userService.getAll(page, size, sortBy, sortDir);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
