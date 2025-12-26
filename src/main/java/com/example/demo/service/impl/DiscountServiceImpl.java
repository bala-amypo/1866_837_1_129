package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DiscountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {
    private final DiscountApplicationRepository discountRepo;
    private final BundleRuleRepository ruleRepo;
    private final CartRepository cartRepo;
    private final CartItemRepository itemRepo;

    public DiscountServiceImpl(DiscountApplicationRepository dr, BundleRuleRepository br, CartRepository cr, CartItemRepository ir) {
        this.discountRepo = dr;
        this.ruleRepo = br;
        this.cartRepo = cr;
        this.itemRepo = ir;
    }

    @Override
    @Transactional
    public List<DiscountApplication> evaluateDiscounts(Long cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow();
        if (!cart.getActive()) return Collections.emptyList();

        discountRepo.deleteByCartId(cartId);
        List<CartItem> items = itemRepo.findByCartId(cartId);
        List<BundleRule> rules = ruleRepo.findByActiveTrue();
        
        List<DiscountApplication> apps = new ArrayList<>();
        Set<Long> productIdsInCart = items.stream().map(i -> i.getProduct().getId()).collect(Collectors.toSet());

        for (BundleRule rule : rules) {
            List<Long> required = Arrays.stream(rule.getRequiredProductIds().split(","))
                                       .map(String::trim).map(Long::valueOf).toList();
            
            if (productIdsInCart.containsAll(required)) {
                BigDecimal total = items.stream()
                    .filter(i -> required.contains(i.getProduct().getId()))
                    .map(i -> i.getProduct().getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                
                DiscountApplication app = new DiscountApplication();
                app.setCart(cart);
                app.setBundleRule(rule);
                app.setDiscountAmount(total.multiply(BigDecimal.valueOf(rule.getDiscountPercentage() / 100.0)));
                app.setAppliedAt(LocalDateTime.now());
                apps.add(discountRepo.save(app));
            }
        }
        return apps;
    }
}