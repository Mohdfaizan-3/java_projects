package com.lg.electronic_store.dao.product;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {

    private Long id;

    private String title;

    private String description;

    private Long categoryId;

    private String imageUrl;

    private BigDecimal price;

    private BigDecimal rating;

    private BigDecimal discount;

    private LocalDate date;

    private boolean live;

    private int availableQuantity;

    private boolean available;

}