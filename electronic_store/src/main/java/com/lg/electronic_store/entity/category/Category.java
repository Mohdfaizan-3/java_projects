package com.lg.electronic_store.entity.category;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", length = 70, nullable = false)
    private String title;

    @Column(name = "description", length = 70)
    private String description;

    @Column(name = "image")
    private String image;
}
