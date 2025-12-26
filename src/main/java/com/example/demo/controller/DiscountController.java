package com.example.demo.controller;

import com.example.demo.model.DiscountApplication;
import com.example.demo.service.impl.DiscountServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/discounts")
@Tag(name = "DiscountController")
public class DiscountController {
    private final DiscountServiceImpl discountService;
    public DiscountController(DiscountServiceImpl ds) { this.discountService = ds; }

    @PostMapping("/evaluate/{cartId}")
    public List<DiscountApplication> evaluate(@PathVariable Long cartId) { 
        return discountService.evaluateDiscounts(cartId); [cite: 223]
    }
}