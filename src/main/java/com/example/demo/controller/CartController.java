package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.service.impl.CartServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/user/{userId}")
    public Cart create(@PathVariable Long userId) {
        return cartService.createCart(userId);
    }

    @GetMapping("/user/{userId}")
    public Cart getActive(@PathVariable Long userId) {
        return cartService.getActiveCartForUser(userId);
    }
}