package com.lg.electronic_store.service.category;

import com.lg.electronic_store.dao.category.CategoryDto;
import com.lg.electronic_store.utils.apiResponse.PagableResponseHelper;

public interface CategoryService {
    CategoryDto create(CategoryDto categoryDto);
    CategoryDto update(CategoryDto categoryDto, Long categoryId);
    void delete(Long id);
    PagableResponseHelper<CategoryDto> getAll(int page, int size, String sortBy, String sortDir);
    CategoryDto get(Long categoryId);
}
