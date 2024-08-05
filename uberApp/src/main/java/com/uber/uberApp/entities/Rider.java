package com.uber.uberApp.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a Rider in the UberApp system.
 * A Rider is associated with a User and has a rating.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;

}