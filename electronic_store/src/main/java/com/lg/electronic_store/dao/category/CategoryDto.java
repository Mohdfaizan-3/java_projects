package com.lg.electronic_store.dao.category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {

    private Long id;

    @NotBlank
    @Min(value = 3, message = "minimun 3 charater")
    private String title;

    @NotBlank(message = "description required")
    private String description;

    private String image;
}
