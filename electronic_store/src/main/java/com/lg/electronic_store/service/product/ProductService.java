package com.lg.electronic_store.service.product;

import com.lg.electronic_store.dao.product.ProductDto;
import com.lg.electronic_store.utils.apiResponse.PageableResponseHelper;

public interface ProductService {

    ProductDto create(ProductDto productDto);

    ProductDto update(ProductDto productDto, Long id);

    void delete(Long id);

    ProductDto get(Long id);

    PageableResponseHelper<ProductDto> getAll(int page, int size, String sortBy, String sortDir);

    PageableResponseHelper<ProductDto> getAllLive(int page, int size, String sortBy, String sortDir);

    PageableResponseHelper<ProductDto> searchByTitle(String title, int page, int size, String sortBy, String sortDir);

}
