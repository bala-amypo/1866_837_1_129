package com.example.demo.controller;

import com.example.demo.entity.DiscountApplication;
import com.example.demo.service.impl.DiscountServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/discounts")
@Tag(name = "DiscountController")
public class DiscountController {

    private final DiscountServiceImpl discountService;

    public DiscountController(DiscountServiceImpl discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/evaluate/{cartId}")
    public List<DiscountApplication> evaluateDiscounts(@PathVariable Long cartId) {
        return discountService.evaluateDiscounts(cartId);
    }

    @GetMapping("/{id}")
    public DiscountApplication getDiscountApplication(@PathVariable Long id) {
        return discountService.getApplicationById(id);
    }

    @GetMapping("/cart/{cartId}")
    public List<DiscountApplication> getDiscountsForCart(@PathVariable Long cartId) {
        return discountService.getApplicationsForCart(cartId);
    }
}