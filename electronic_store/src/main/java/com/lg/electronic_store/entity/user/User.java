package com.lg.electronic_store.entity.user;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "user_image")
    private String profileImage;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "profile_image_id", referencedColumnName = "id")
//    private Image profileImage;
}
