package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl {
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cr) {
        this.cartRepository = cr;
    }

    public Cart createCart(Long userId) {
        // Check for existing active cart [cite: 205]
        return cartRepository.findByUserIdAndActiveTrue(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            newCart.setActive(true); // Default to true [cite: 165, 205]
            return cartRepository.save(newCart);
        });
    }

    public Cart getActiveCartForUser(Long userId) {
        // Must throw specific exception message for tests [cite: 192, 206]
        return cartRepository.findByUserIdAndActiveTrue(userId)
                .orElseThrow(() -> new EntityNotFoundException("Active cart not found")); [cite: 192, 206]
    }
}