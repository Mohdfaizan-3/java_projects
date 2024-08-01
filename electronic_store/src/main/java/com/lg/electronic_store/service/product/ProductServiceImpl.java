package com.lg.electronic_store.service.product;

import com.lg.electronic_store.dao.product.ProductDto;
import com.lg.electronic_store.entity.category.Category;
import com.lg.electronic_store.entity.product.Product;
import com.lg.electronic_store.exception.ResourceNotFoundException;
import com.lg.electronic_store.repository.category.CategoryRepo;
import com.lg.electronic_store.repository.product.ProductRepository;
import com.lg.electronic_store.utils.apiResponse.PageableResponseHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepo categoryRepo;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryRepo categoryRepo) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryRepo = categoryRepo;
    }

    @Override
    @Transactional
    public ProductDto create(ProductDto productDto) {
        productDto.setDate(LocalDate.now());
        Category category = categoryRepo.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Product product = modelMapper.map(productDto, Product.class);
        product.setCategory(category);
        Product saved = productRepository.save(product);
        ProductDto map = modelMapper.map(saved, ProductDto.class);
        return map;
    }

    @Override
    @Transactional
    public ProductDto update(ProductDto productDto, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setAvailableQuantity(productDto.getAvailableQuantity());
        product.setImageUrl(productDto.getImageUrl());
        product.setAvailable(productDto.isAvailable());
        product.setLive(productDto.isLive());
        product.setDiscount(productDto.getDiscount());
        product.setRating(productDto.getRating());
        Category category = categoryRepo.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        product.setCategory(category);
        Product saved = productRepository.save(product);
        return modelMapper.map(saved, ProductDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
    }

    @Override
    public ProductDto get(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public PageableResponseHelper<ProductDto> getAll(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        Page<Product> pages = productRepository.findAll(pageRequest);
        return PageableResponseHelper.getPageableResponse(pages, ProductDto.class);
    }

    @Override
    public PageableResponseHelper<ProductDto> getAllLive(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        Page<Product> pages = productRepository.findByLiveTrue(pageRequest);
        return PageableResponseHelper.getPageableResponse(pages, ProductDto.class);
    }

    @Override
    public PageableResponseHelper<ProductDto> searchByTitle(
            String title, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        Page<Product> pages = productRepository.findByTitleContaining(title, pageRequest);
        return PageableResponseHelper.getPageableResponse(pages, ProductDto.class);
    }


}
