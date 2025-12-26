package com.example.demo.service.impl;

import com.example.demo.entity.DiscountApplication;
import com.example.demo.repository.DiscountApplicationRepository;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CartItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiscountServiceImpl {
    private final DiscountApplicationRepository discountRepo;
    private final BundleRuleRepository ruleRepo;
    private final CartRepository cartRepo;
    private final CartItemRepository itemRepo;

    public DiscountServiceImpl(DiscountApplicationRepository discountRepo, 
                               BundleRuleRepository ruleRepo, 
                               CartRepository cartRepo, 
                               CartItemRepository itemRepo) {
        this.discountRepo = discountRepo;
        this.ruleRepo = ruleRepo;
        this.cartRepo = cartRepo;
        this.itemRepo = itemRepo;
    }

    public List<DiscountApplication> evaluateDiscounts(Long cartId) {
        return discountRepo.findByCartId(cartId);
    }

    public DiscountApplication getApplicationById(Long id) {
        return discountRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discount not found"));
    }

    public List<DiscountApplication> getApplicationsForCart(Long cartId) {
        return discountRepo.findByCartId(cartId);
    }
}