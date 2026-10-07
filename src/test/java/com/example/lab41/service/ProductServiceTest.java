package com.example.lab41.service;

import com.example.lab41.dto.ProductRequest;
import com.example.lab41.exception.ResourceNotFoundException;
import com.example.lab41.repository.CategoryRepository;
import com.example.lab41.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository products;

    @Mock
    CategoryRepository categories;

    @InjectMocks
    ProductService service;

    @Test
    void create_shouldRejectMissingCategory() {
        when(categories.existsById("missing")).thenReturn(false);
        ProductRequest request = new ProductRequest("Keyboard", new BigDecimal("120"), 10, "missing");
        assertThatThrownBy(() -> service.create(request))
            .isInstanceOf(ResourceNotFoundException.class);
        verify(products, never()).save(any());
    }
}
