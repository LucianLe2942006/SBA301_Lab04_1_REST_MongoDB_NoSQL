package com.example.lab41.repository;

import com.example.lab41.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Product> findByCategoryId(String categoryId, Pageable pageable);
    Page<Product> findByNameContainingIgnoreCaseAndCategoryId(String keyword, String categoryId, Pageable pageable);
}
