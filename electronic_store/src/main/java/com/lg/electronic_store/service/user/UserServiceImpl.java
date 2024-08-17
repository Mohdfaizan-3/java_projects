package com.lg.electronic_store.service.user;

import com.lg.electronic_store.dao.user.UserDTO;
import com.lg.electronic_store.entity.user.User;
import com.lg.electronic_store.exception.ResourceNotFoundException;
import com.lg.electronic_store.repository.user.UserRepository;
import com.lg.electronic_store.utils.apiResponse.PageableResponseHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Value("${user.profile.image.path}")
    private String imageUploadPath;

//    private final ImageRepository imageRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
//        this.imageRepository = imageRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    @Transactional
//    public UserDTO create(UserDTO userDTO) {
//        Optional<User> isUserExist = userRepository.findByEmail(userDTO.getEmail());
//        if(isUserExist.isPresent()) {
//            throw new BadCredentialsException("User with email already exits "+ userDTO.getEmail());
//        }
//        User user = dtoToEntity(userDTO);
//       user.setPassword(passwordEncoder.encode(user.getPassword()));
//        User savedUser = userRepository.save(user);
//        return entityToDto(savedUser);
//    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO, String id) {

        User user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setProfileImage(userDTO.getProfileImage());
        user.setRoles(userDTO.getRoles());
        User updatedUser = userRepository.save(user);
        return entityToDto(updatedUser);
    }

    @Transactional
    @Override
    public void delete(String id) {
        User user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (user.getProfileImage() == null) {
            userRepository.delete(user);
            return;
        }

        String fullPath = imageUploadPath + File.separator + user.getProfileImage();
        Path path = Paths.get(fullPath);
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        userRepository.delete(user);
    }

    @Override
    public PageableResponseHelper<UserDTO> getAll(int page, int size, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        Page<User> pages = userRepository.findAll(pageRequest);
        return PageableResponseHelper.getPageableResponse(pages, UserDTO.class);
    }

    @Override
    public UserDTO getUser(String id) {
        User user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return entityToDto(user);
    }

    @Override
    public UserDTO partialUpdate(Long id, Map<String, Object> updates) {
        User user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(User.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, user, value);
        });

        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return entityToDto(user);
    }

    private UserDTO entityToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }


    private User dtoToEntity(UserDTO user) {
        return modelMapper.map(user, User.class);
    }


    //  @Override
//    public ResponseEntity<?> uploadImage(MultipartFile file, String id) throws IOException {
//
//        Image imageData = imageRepository.save(Image.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .imageData(ImageUtils.compressImage(file.getBytes())).build());
//
//        if (imageData != null) {
//            return "File uploaded successfully";
//        }
//
//        User user = userRepository.findById(Long.valueOf(id))
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//        user.setProfileImage(imageData);
//        User updatedUser = userRepository.save(user);
//
//        return updatedUser;
//    }

//    @Override
//    public byte[] downloadImage(Long id) {
//        Optional<Image> dbImageData = imageRepository.findById(id);
//        if (dbImageData.isPresent()) {
//            return ImageUtils.decompressImage(dbImageData.get().getImageData());
//        } else {
//            throw new ResourceNotFoundException("Image not found with id: " + id);
//        }
//    }
}
