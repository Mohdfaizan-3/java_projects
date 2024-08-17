package com.lg.electronic_store.dao.user;

import com.lg.electronic_store.entity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @Size(max = 15, message = "enter less than 16 character")
    @NotBlank(message = "enter name first")
    private String name;

    @Email(message = "enter valid email")
    @NotBlank(message = "enter email")
    private String email;

    @NotBlank(message = "enter password")
    private String password;

    private String profileImage;

    private Set<Role> roles;
}
