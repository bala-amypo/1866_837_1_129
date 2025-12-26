package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.service.impl.CartServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@Tag(name = "CartController")
public class CartController {
    private final CartServiceImpl cartService;
    public CartController(CartServiceImpl cs) { this.cartService = cs; }

    @PostMapping("/user/{userId}")
    public Cart createOrRetrieve(@PathVariable Long userId) { return cartService.createCart(userId); } [cite: 220]

    @GetMapping("/user/{userId}")
    public Cart getActive(@PathVariable Long userId) { return cartService.getActiveCartForUser(userId); } [cite: 221]
}