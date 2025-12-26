package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService ps) { this.productService = ps; }

    @PostMapping("/")
    public Product create(@RequestBody Product p) { return productService.createProduct(p); }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) { return productService.updateProduct(id, p); }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) { return productService.getProductById(id); }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) { productService.deactivateProduct(id); }
}