package com.lg.electronic_store.service.user;

import com.lg.electronic_store.dao.user.UserDTO;
import com.lg.electronic_store.utils.apiResponse.PageableResponseHelper;

import java.util.Map;

public interface UserService {

//    UserDTO create(UserDTO user);

    UserDTO update(UserDTO user, String id);

    void delete(String id);

    PageableResponseHelper<UserDTO> getAll(int page, int size, String sortBy, String sortDir);

    UserDTO getUser(String id);

    UserDTO partialUpdate(Long id, Map<String, Object> updates);

    UserDTO getUser(Long userId);

//    String uploadImage(MultipartFile file, UserDTO userRequest) throws IOException;
//    byte[] downloadImage(Long id);
}
