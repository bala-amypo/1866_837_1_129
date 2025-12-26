package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CartItemService;
import java.util.List;
import java.util.Optional;

public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CartItem addItemToCart(CartItem item) {
        Cart cart = cartRepository.findById(item.getCart().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        Product product = productRepository.findById(item.getProduct().getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (!cart.getActive()) {
            throw new IllegalArgumentException("Cannot add to inactive carts");
        }
        if (item.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());
        if (existingItem.isPresent()) {
            CartItem ci = existingItem.get();
            ci.setQuantity(ci.getQuantity() + item.getQuantity());
            return cartItemRepository.save(ci);
        }

        item.setCart(cart);
        item.setProduct(product);
        return cartItemRepository.save(item);
    }

    @Override
    public List<CartItem> getItemsForCart(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }
}