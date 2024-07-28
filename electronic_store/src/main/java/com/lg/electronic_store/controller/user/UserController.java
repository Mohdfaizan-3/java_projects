package com.lg.electronic_store.controller.user;

import com.lg.electronic_store.dao.user.UserRequest;
import com.lg.electronic_store.service.file.FileService;
import com.lg.electronic_store.service.user.UserService;
import com.lg.electronic_store.utils.Image.ImageResponse;
import com.lg.electronic_store.utils.apiResponse.ApiResponse;
import com.lg.electronic_store.utils.apiResponse.PageableResponseHelper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final FileService fileService;

    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    @Autowired
    public UserController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<UserRequest> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserRequest userRequest1 = userService.create(userRequest);
        return new ResponseEntity<>(userRequest1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRequest> updateUser(
            @Valid @RequestBody UserRequest userRequest, @PathVariable("id") String id) {

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
    public ResponseEntity<PageableResponseHelper<UserRequest>> getAllUsers(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "5", required = false) int size,
            @RequestParam(value = "sortBy", defaultValue = "username", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        PageableResponseHelper<UserRequest> users = userService.getAll(page, size, sortBy, sortDir);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/{userId}/uploadImage")
    public ResponseEntity<ImageResponse> uploadUserImage(
            @RequestParam("userImage") MultipartFile file,
            @PathVariable("userId") String id) throws IOException {

        String imageName = fileService.uploadFile(file, imageUploadPath);
        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName)
                .success(true).message("created").build();
        UserRequest user = userService.getUser(id);
        user.setProfileImage(imageName);
        userService.update(user, id);
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/getProfileImg")
    public void downloadUserImage(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        UserRequest user = userService.getUser(id);
        InputStream resource = fileService.getResource(imageUploadPath, user.getProfileImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserRequest> partialUpdate(
            @PathVariable(name = "id") Long id,@RequestBody Map<String, Object> updates) {

        UserRequest userDto = userService.partialUpdate(id, updates);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}



