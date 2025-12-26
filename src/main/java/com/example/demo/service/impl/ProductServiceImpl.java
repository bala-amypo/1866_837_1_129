package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class ProductServiceImpl {
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository pr) { this.productRepository = pr; }

    public Product createProduct(Product p) {
        if (productRepository.findBySku(p.getSku()).isPresent()) 
            throw new IllegalArgumentException("Duplicate SKU"); [cite: 199]
        if (p.getPrice().compareTo(BigDecimal.ZERO) <= 0) 
            throw new IllegalArgumentException("Price must be positive"); [cite: 200]
        return productRepository.save(p);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> 
            new EntityNotFoundException("Product not found")); [cite: 201]
    }

    public Product updateProduct(Long id, Product updated) {
        Product existing = getProductById(id);
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        return productRepository.save(existing); [cite: 201]
    }

    public void deactivateProduct(Long id) {
        Product p = getProductById(id);
        p.setActive(false);
        productRepository.save(p); [cite: 202]
    }
}