package com.lg.electronic_store.service.user;

import com.lg.electronic_store.dao.user.UserRequest;
import com.lg.electronic_store.utils.apiResponse.PagableResponseHelper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    UserRequest create(UserRequest user);
    UserRequest update(UserRequest user, String id);
    void delete(String id);
    PagableResponseHelper<UserRequest> getAll(int page, int size, String sortBy, String sortDir);
    UserRequest getUser(String id);
//    String uploadImage(MultipartFile file, UserRequest userRequest) throws IOException;
//    byte[] downloadImage(Long id);
}
