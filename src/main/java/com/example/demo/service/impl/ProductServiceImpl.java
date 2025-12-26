package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository pr) { this.productRepository = pr; }

    @Override
    public Product createProduct(Product p) {
        if (productRepository.findBySku(p.getSku()).isPresent()) 
            throw new IllegalArgumentException("SKU already exists");
        if (p.getPrice().compareTo(BigDecimal.ZERO) <= 0) 
            throw new IllegalArgumentException("Price must be positive");
        return productRepository.save(p);
    }

    @Override
    public Product updateProduct(Long id, Product updated) {
        Product existing = getProductById(id);
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        return productRepository.save(existing);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> 
            new EntityNotFoundException("Product not found"));
    }

    @Override
    public void deactivateProduct(Long id) {
        Product p = getProductById(id);
        p.setActive(false);
        productRepository.save(p);
    }
}