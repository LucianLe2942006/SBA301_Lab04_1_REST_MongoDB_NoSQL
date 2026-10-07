package com.example.lab41.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
    String id,
    String name,
    BigDecimal price,
    Integer stock,
    String categoryId,
    List<ReviewResponse> reviews
) {
}
