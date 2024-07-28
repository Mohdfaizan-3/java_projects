package com.lg.electronic_store.dao.product;

import com.lg.electronic_store.dao.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String title;

    private String description;

    private Long categoryId;

//   private CategoryDto category;

    private String imageUrl;

    private BigDecimal price;

    private BigDecimal rating;

    private BigDecimal discount;

    private int quantity;

    private LocalDate date;

    private boolean live;

    private boolean available;

}
