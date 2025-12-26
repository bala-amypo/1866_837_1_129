package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DiscountService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class DiscountServiceImpl implements DiscountService {
    private final DiscountApplicationRepository discountApplicationRepository;
    private final BundleRuleRepository bundleRuleRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public DiscountServiceImpl(DiscountApplicationRepository d, BundleRuleRepository b, CartRepository c, CartItemRepository i) {
        this.discountApplicationRepository = d;
        this.bundleRuleRepository = b;
        this.cartRepository = c;
        this.cartItemRepository = i;
    }

    @Override
    public List<DiscountApplication> evaluateDiscounts(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        
        if (!cart.getActive()) {
            return Collections.emptyList();
        }

        List<CartItem> items = cartItemRepository.findByCartId(cartId);
        List<BundleRule> rules = bundleRuleRepository.findByActiveTrue();
        
        discountApplicationRepository.deleteByCartId(cartId);

        List<DiscountApplication> appliedDiscounts = new ArrayList<>();
        Set<Long> productIdsInCart = items.stream()
                .map(item -> item.getProduct().getId())
                .collect(Collectors.toSet());

        for (BundleRule rule : rules) {
            List<Long> requiredIds = Arrays.stream(rule.getRequiredProductIds().split(","))
                    .map(String::trim)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());

            if (productIdsInCart.containsAll(requiredIds)) {
                BigDecimal totalApplicablePrice = items.stream()
                        .filter(item -> requiredIds.contains(item.getProduct().getId()))
                        .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                DiscountApplication app = new DiscountApplication();
                app.setCart(cart);
                app.setBundleRule(rule);
                app.setDiscountAmount(totalApplicablePrice.multiply(BigDecimal.valueOf(rule.getDiscountPercentage() / 100.0)));
                app.setAppliedAt(LocalDateTime.now());
                appliedDiscounts.add(discountApplicationRepository.save(app));
            }
        }
        return appliedDiscounts;
    }
}