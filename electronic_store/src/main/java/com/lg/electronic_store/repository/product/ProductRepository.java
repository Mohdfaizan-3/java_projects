package com.lg.electronic_store.repository.product;

import com.lg.electronic_store.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByTitleContaining(String title, Pageable pageable);
    Page<Product> findByLiveTrue(Pageable pageable);
}
