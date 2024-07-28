package com.lg.electronic_store.controller.category;

import com.lg.electronic_store.dao.category.CategoryDto;
import com.lg.electronic_store.service.category.CategoryService;
import com.lg.electronic_store.utils.apiResponse.ApiResponse;
import com.lg.electronic_store.utils.apiResponse.PageableResponseHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto category) {
        CategoryDto categoryDto = categoryService.create(category);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto, @PathVariable Long id) {
        CategoryDto update = categoryService.update(categoryDto, id);
        return new ResponseEntity<>(update, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        categoryService.delete(id);
        ApiResponse categoryDeleted = ApiResponse.builder().message("category deleted").success(true)
                .status(HttpStatus.OK).build();
        return new ResponseEntity<>(categoryDeleted, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageableResponseHelper<CategoryDto>> getAll(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "5", required = false) int size,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        PageableResponseHelper<CategoryDto> pagableResponse = categoryService.getAll(page, size, sortBy, sortDir);
        return new ResponseEntity<>(pagableResponse, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> get(@PathVariable Long id) {
        CategoryDto categoryDto = categoryService.get(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDto> partialUpdate(
            @PathVariable(name = "id") Long id,
            @RequestBody Map<String, Object> updates) {
        CategoryDto categoryDto = categoryService.partialUpdate(id, updates);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }
}
