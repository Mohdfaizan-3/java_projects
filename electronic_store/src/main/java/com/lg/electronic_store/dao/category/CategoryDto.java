package com.lg.electronic_store.dao.category;

import com.lg.electronic_store.entity.product.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {

    private Long id;

    private String title;

    private String description;

    private String image;

    private List<Product> products = new ArrayList<>();

}
