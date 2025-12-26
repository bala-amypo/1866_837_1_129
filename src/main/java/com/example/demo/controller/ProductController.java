package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Tag(name = "ProductController")
public class ProductController {
    private final ProductServiceImpl productService;
    public ProductController(ProductServiceImpl ps) { this.productService = ps; }

    @PostMapping("/")
    public Product create(@RequestBody Product p) { return productService.createProduct(p); } [cite: 218]

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) { return productService.updateProduct(id, p); } [cite: 219]

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) { return productService.getProductById(id); } [cite: 219]

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) { productService.deactivateProduct(id); } [cite: 220]
}