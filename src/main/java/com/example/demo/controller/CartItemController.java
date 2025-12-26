package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.service.impl.CartItemServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
@Tag(name = "CartItemController")
public class CartItemController {
    private final CartItemServiceImpl cartItemService;
    public CartItemController(CartItemServiceImpl cis) { this.cartItemService = cis; }

    @PostMapping("/")
    public CartItem addItem(@RequestBody CartItem item) { return cartItemService.addItemToCart(item); } [cite: 222]

    @GetMapping("/cart/{cartId}")
    public List<CartItem> getItems(@PathVariable Long cartId) { return cartItemService.getItemsForCart(cartId); } [cite: 223]
}