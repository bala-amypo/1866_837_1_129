package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartItemServiceImpl {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemServiceImpl(CartItemRepository cir, CartRepository cr, ProductRepository pr) {
        this.cartItemRepository = cir;
        this.cartRepository = cr;
        this.productRepository = pr;
    }

    public CartItem addItemToCart(CartItem item) {
        Cart cart = cartRepository.findById(item.getCart().getId()).orElseThrow();
        Product product = productRepository.findById(item.getProduct().getId()).orElseThrow();
        
        if (!cart.getActive()) throw new IllegalArgumentException("Cannot add to inactive carts"); [cite: 209]
        if (item.getQuantity() <= 0) throw new IllegalArgumentException("Quantity must be positive"); [cite: 210]

        return cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId())
            .map(existing -> {
                existing.setQuantity(existing.getQuantity() + item.getQuantity());
                return cartItemRepository.save(existing);
            })
            .orElseGet(() -> cartItemRepository.save(item)); [cite: 210]
    }

    public List<CartItem> getItemsForCart(Long cartId) {
        return cartItemRepository.findByCartId(cartId); [cite: 211]
    }
}