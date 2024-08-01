package com.lg.electronic_store.entity.product;

import com.lg.electronic_store.entity.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    @Digits(integer = 10, fraction = 2, message = "Price must be a valid monetary amount")
    private BigDecimal price;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
    @Digits(integer = 1, fraction = 1, message = "Rating must be a valid decimal between 0 and 5")
    private BigDecimal rating;

    @DecimalMin(value = "0.0", message = "Discount must be at least 0.0")
    @DecimalMax(value = "100.0", message = "Discount must be at most 100.0")
    @Digits(integer = 3, fraction = 2, message = "Discount must be a valid percentage")
    private BigDecimal discount;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date must be in the past or present")
    private LocalDate date;

    private boolean live;

    private boolean available;

    @Min(value = 0, message = "Quantity must be at least 0")
    private int availableQuantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}