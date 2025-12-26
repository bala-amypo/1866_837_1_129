package com.example.demo.service.impl;

import com.example.demo.model.DiscountApplication;
import com.example.demo.repository.*;
import com.example.demo.service.DiscountService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    private final DiscountApplicationRepository discountRepo;
    private final BundleRuleRepository ruleRepo;
    private final CartRepository cartRepo;
    private final CartItemRepository itemRepo;

    public DiscountServiceImpl(DiscountApplicationRepository d, BundleRuleRepository r, CartRepository c, CartItemRepository i) {
        this.discountRepo = d; this.ruleRepo = r; this.cartRepo = c; this.itemRepo = i;
    }

    @Override
    public List<DiscountApplication> evaluateDiscounts(Long cartId) {
        return discountRepo.findByCartId(cartId);
    }
}