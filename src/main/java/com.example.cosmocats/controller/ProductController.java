package com.example.cosmocats.controller;

import com.example.cosmocats.exception.ResourceNotFoundException;
import com.example.cosmocats.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private Map<Long, Product> productStorage = new HashMap<>();

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productStorage.get(id);
        if (product == null) {
            throw new ResourceNotFoundException("Product with ID " + id + " not found");
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        productStorage.put(product.getId(), product);
        return product;
    }
}
