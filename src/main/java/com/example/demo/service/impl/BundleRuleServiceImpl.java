package com.example.demo.service.impl;

import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;
import org.springframework.stereotype.Service;

@Service
public class BundleRuleServiceImpl {
    private final BundleRuleRepository bundleRuleRepository;

    public BundleRuleServiceImpl(BundleRuleRepository brr) {
        this.bundleRuleRepository = brr;
    }

    public BundleRule createRule(BundleRule rule) {
        // Validation for discount range [cite: 160, 203]
        if (rule.getDiscountPercentage() < 0 || rule.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100"); [cite: 160, 203]
        }
        // Validation for non-empty required products [cite: 161, 203]
        if (rule.getRequiredProductIds() == null || rule.getRequiredProductIds().trim().isEmpty()) {
            throw new IllegalArgumentException("Required product IDs cannot be empty"); [cite: 161, 203]
        }
        return bundleRuleRepository.save(rule);
    }
}