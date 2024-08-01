package com.lg.electronic_store.service.user;

import com.lg.electronic_store.dao.category.CategoryDto;
import com.lg.electronic_store.dao.user.UserRequest;
import com.lg.electronic_store.entity.user.User;
import com.lg.electronic_store.utils.apiResponse.PageableResponseHelper;

import java.util.Map;

public interface UserService {

    UserRequest create(UserRequest user);

    UserRequest update(UserRequest user, String id);

    void delete(String id);

    PageableResponseHelper<UserRequest> getAll(int page, int size, String sortBy, String sortDir);

    UserRequest getUser(String id);

    UserRequest partialUpdate(Long id, Map<String, Object> updates);

    UserRequest getUser(Long userId);

//    String uploadImage(MultipartFile file, UserRequest userRequest) throws IOException;
//    byte[] downloadImage(Long id);
}
