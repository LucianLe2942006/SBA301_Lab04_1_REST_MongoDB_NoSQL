package com.example.lab41.service;

import com.example.lab41.dto.ProductRequest;
import com.example.lab41.dto.ProductResponse;
import com.example.lab41.dto.ReviewRequest;
import com.example.lab41.dto.ReviewResponse;
import com.example.lab41.exception.ResourceNotFoundException;
import com.example.lab41.model.Product;
import com.example.lab41.model.Review;
import com.example.lab41.repository.CategoryRepository;
import com.example.lab41.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {
    private static final Set<String> SORT_FIELDS = Set.of("name", "price", "stock");
    private final ProductRepository products;
    private final CategoryRepository categories;

    public ProductService(ProductRepository products, CategoryRepository categories) {
        this.products = products;
        this.categories = categories;
    }

    public ProductResponse findById(String id) {
        return toResponse(getRequired(id));
    }

    public ProductResponse create(ProductRequest request) {
        validateCategory(request.categoryId());
        Product p = new Product();
        apply(p, request);
        return toResponse(products.save(p));
    }

    public ProductResponse update(String id, ProductRequest request) {
        Product p = getRequired(id);
        validateCategory(request.categoryId());
        apply(p, request);
        return toResponse(products.save(p));
    }

    public void delete(String id) {
        Product p = getRequired(id);
        products.delete(p);
    }

    private Product getRequired(String id) {
        return products.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }

    private void validateCategory(String id) {
        if (id != null && !categories.existsById(id)) {
            throw new ResourceNotFoundException("Category not found: " + id);
        }
    }

    private void apply(Product p, ProductRequest r) {
        p.setName(r.name());
        p.setPrice(r.price());
        p.setStock(r.stock());
        p.setCategoryId(r.categoryId());
    }

    public Page<ProductResponse> search(
        String keyword,
        String categoryId,
        int page,
        int size,
        String sortBy,
        String direction
    ) {
        if (!SORT_FIELDS.contains(sortBy)) {
            throw new IllegalArgumentException("Unsupported sort field");
        }
        int safeSize = Math.min(Math.max(size, 1), 50);
        Sort.Direction dir = "desc".equalsIgnoreCase(direction)
            ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(Math.max(page, 0), safeSize, Sort.by(dir, sortBy));
        Page<Product> result;
        if (!keyword.isBlank() && categoryId != null) {
            result = products.findByNameContainingIgnoreCaseAndCategoryId(keyword, categoryId, pageable);
        } else if (!keyword.isBlank()) {
            result = products.findByNameContainingIgnoreCase(keyword, pageable);
        } else if (categoryId != null) {
            result = products.findByCategoryId(categoryId, pageable);
        } else {
            result = products.findAll(pageable);
        }
        return result.map(this::toResponse);
    }

    public ProductResponse addReview(String productId, ReviewRequest request) {
        Product p = getRequired(productId);
        Review review = new Review(
            request.userId(),
            request.rating(),
            request.comment(),
            Instant.now()
        );
        p.getReviews().add(review);
        return toResponse(products.save(p));
    }

    public ProductResponse toResponse(Product product) {
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        if (product.getReviews() != null) {
            for (Review r : product.getReviews()) {
                reviewResponses.add(new ReviewResponse(
                    r.getUserId(),
                    r.getRating(),
                    r.getComment(),
                    r.getCreatedAt()
                ));
            }
        }
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getStock(),
            product.getCategoryId(),
            reviewResponses
        );
    }
}
