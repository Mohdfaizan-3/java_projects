package com.lg.electronic_store.dao;

import com.lg.electronic_store.entity.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {
    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
}
