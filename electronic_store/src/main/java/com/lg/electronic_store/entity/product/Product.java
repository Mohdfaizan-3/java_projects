package com.lg.electronic_store.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lg.electronic_store.entity.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "title required")
    @Size(min = 3, max = 100, message = "title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "description required")
    @Size(max = 500, message = "description must be less than 500 characters")
    private String description;

    @Column(name = "image")
    @NotBlank(message = "image required")
//    @Size(max = 200, message = "image URL must be less than 200 characters")
//    @Pattern(regexp = "(http|https)://.*", message = "image URL must be a valid URL")
    private String imageUrl;

    @NotNull(message = "price required")
    @DecimalMin(value = "0.0", inclusive = false, message = "price must be positive")
    @Digits(integer = 10, fraction = 2, message = "price must be a valid monetary amount")
    private BigDecimal price;

    @DecimalMin(value = "0.0", message = "rating must be at least 0.0")
    @DecimalMax(value = "5.0", message = "rating must be at most 5.0")
    @Digits(integer = 1, fraction = 1, message = "rating must be a valid decimal between 0 and 5")
    private BigDecimal rating;

    @DecimalMin(value = "0.0", message = "discount must be at least 0%")
    @DecimalMax(value = "100.0", message = "discount must be at most 100%")
    @Digits(integer = 3, fraction = 2, message = "discount must be a valid percentage")
    private BigDecimal discount;

    @Min(value = 0, message = "quantity must be at least 0")
    @Max(value = 10000, message = "quantity must be less than 10000")
    private int quantity;

    @NotNull(message = "date required")
    @PastOrPresent(message = "date must be in the past or present")
    private LocalDate date;

    private boolean live;

    private boolean available;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity && live == product.live && available == product.available
                && Objects.equals(id, product.id) && Objects.equals(title, product.title)
                && Objects.equals(description, product.description) && Objects.equals(imageUrl, product.imageUrl)
                && Objects.equals(price, product.price) && Objects.equals(rating, product.rating)
                && Objects.equals(discount, product.discount) && Objects.equals(date, product.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, imageUrl, price, rating, discount, quantity, date, live, available);
    }
}
