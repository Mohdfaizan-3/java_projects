package com.lg.electronic_store.service.category;

import com.lg.electronic_store.dao.category.CategoryDto;
import com.lg.electronic_store.entity.category.Category;
import com.lg.electronic_store.exception.ResourceNotFoundException;
import com.lg.electronic_store.repository.category.CategoryRepo;
import com.lg.electronic_store.utils.apiResponse.PageableResponseHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, Long categoryId) {
        Category category = findCategoryById(categoryId);
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        categoryDto.setImage(categoryDto.getImage());

        Category savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public void delete(Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category not found"));
        categoryRepo.delete(category);
    }

    @Override
    public PageableResponseHelper<CategoryDto> getAll(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        Page<Category> pages = categoryRepo.findAll(pageRequest);
        return PageableResponseHelper.getPageableResponse(pages, CategoryDto.class);
    }

    @Override
    public CategoryDto get(Long categoryId) {
        Category category = findCategoryById(categoryId);
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto partialUpdate(Long id, Map<String, Object> updates) {
        Category categoryEntity = findCategoryById(id);
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(Category.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, categoryEntity, value);
        });

        return modelMapper.map(categoryRepo.save(categoryEntity), CategoryDto.class);
    }

    private Category findCategoryById(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("category not found"));
    }
}
