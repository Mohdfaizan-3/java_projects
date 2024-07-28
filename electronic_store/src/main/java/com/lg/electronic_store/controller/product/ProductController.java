package com.lg.electronic_store.controller.product;

import com.lg.electronic_store.dao.product.ProductDto;
import com.lg.electronic_store.dao.user.UserRequest;
import com.lg.electronic_store.service.file.FileService;
import com.lg.electronic_store.service.product.ProductService;
import com.lg.electronic_store.utils.Image.ImageResponse;
import com.lg.electronic_store.utils.apiResponse.ApiResponse;
import com.lg.electronic_store.utils.apiResponse.PageableResponseHelper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;


    private final FileService fileService;

    @Value("${product.imageUrl.path}")
    private String imagePath;

    @Autowired
    public ProductController(ProductService productService, FileService fileService) {
        this.productService = productService;
        this.fileService = fileService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto productDto) {
        ProductDto productDto1 = productService.create(productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(
            @Valid @RequestBody ProductDto productDto, @PathVariable(name = "id") Long id) {
        ProductDto productDto1 = productService.update(productDto, id);
        return new ResponseEntity<>(productDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        productService.delete(id);
        ApiResponse productDeleted = ApiResponse.builder().message("product deleted").success(true)
                .status(HttpStatus.OK).build();
        return new ResponseEntity<>(productDeleted, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> create(@PathVariable Long id) {
        ProductDto productDto1 = productService.get(id);
        return new ResponseEntity<>(productDto1, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<PageableResponseHelper<ProductDto>> getAll(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "5", required = false) int size,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        PageableResponseHelper<ProductDto> pageableResponse = productService.getAll(page, size, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.FOUND);
    }

    @GetMapping("/liveProducts")
    public ResponseEntity<PageableResponseHelper<ProductDto>> getAllLive(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "5", required = false) int size,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        PageableResponseHelper<ProductDto> pageableResponse = productService.getAllLive(page, size, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.FOUND);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponseHelper<ProductDto>> searchByTitle(
            @PathVariable String query, @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "5", required = false) int size,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        PageableResponseHelper<ProductDto> pageableResponse = productService.searchByTitle(query, page, size, sortBy,
                sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.FOUND);
    }

    @PostMapping("/uploadImage/{id}")
    public ResponseEntity<ImageResponse> uploadImage(
            @PathVariable Long id, @RequestParam("productImage") MultipartFile image) throws IOException {
        String fileName = fileService.uploadFile(image, imagePath);
        ProductDto productDto = productService.get(id);
        productDto.setImageUrl(fileName);
        ProductDto updateProduct = productService.update(productDto, id);

        ImageResponse response = ImageResponse.builder().imageName(updateProduct.getImageUrl()).message("product image uploaded").status(HttpStatus.CREATED).success(true).build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/getImg")
    public void downloadUserImage(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        ProductDto productDto = productService.get(id);
        InputStream resource = fileService.getResource(imagePath, productDto.getImageUrl());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
