package com.lg.electronic_store.service.user;

import com.lg.electronic_store.dao.user.UserRequest;
import com.lg.electronic_store.entity.user.User;
import com.lg.electronic_store.exception.ResourceNotFoundException;
import com.lg.electronic_store.repository.user.UserRepository;
import com.lg.electronic_store.utils.apiResponse.PagableResponseHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public UserRequest create(UserRequest userRequest) {
        User user = dtoToEntity(userRequest);
        User savedUser = userRepository.save(user);
        return entityToDto(savedUser);
    }

    @Override
    @Transactional
    public UserRequest update(UserRequest userRequest, String id) {

        User user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        User updatedUser = userRepository.save(user);
        return entityToDto(updatedUser);
    }

    @Transactional
    @Override
    public void delete(String id) {
        User user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public PagableResponseHelper<UserRequest> getAll(int page, int size, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        Page<User> pages = userRepository.findAll(pageRequest);
        return PagableResponseHelper.getPagableResponse(pages, UserRequest.class);
    }

    @Override
    public UserRequest getUser(String id) {
        User user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return entityToDto(user);
    }

    private UserRequest entityToDto(User user) {
        return modelMapper.map(user, UserRequest.class);
    }


    private User dtoToEntity(UserRequest user) {
        return modelMapper.map(user, User.class);
    }
}
