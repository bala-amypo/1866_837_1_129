package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@Tag(name = "CartController")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cs) { this.cartService = cs; }

    @PostMapping("/user/{userId}")
    public Cart createOrRetrieve(@PathVariable Long userId) { 
        return cartService.createCart(userId); 
    }

    @GetMapping("/user/{userId}")
    public Cart getActive(@PathVariable Long userId) { 
        return cartService.getActiveCartForUser(userId); 
    }
}