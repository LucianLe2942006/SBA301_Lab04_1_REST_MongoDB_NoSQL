package com.example.lab41;

import com.example.lab41.model.Category;
import com.example.lab41.model.Product;
import com.example.lab41.repository.CategoryRepository;
import com.example.lab41.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootApplication
public class Lab41Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab41Application.class, args);
    }

    @Bean
    CommandLineRunner seed(CategoryRepository categories, ProductRepository products) {
        return args -> {
            if (categories.count() == 0) {
                Category accessories = categories.save(new Category(null, "Accessories"));
                products.save(new Product(
                    null,
                    "Mechanical Keyboard",
                    new BigDecimal("120.00"),
                    20,
                    accessories.getId(),
                    new ArrayList<>()
                ));
            }
        };
    }
}
