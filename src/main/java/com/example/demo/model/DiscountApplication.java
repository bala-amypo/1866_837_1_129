package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class DiscountApplication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne private Cart cart;
    @ManyToOne private BundleRule bundleRule;
    private BigDecimal discountAmount;
    private LocalDateTime appliedAt;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Cart getCart() { return cart; }
    public void setCart(Cart cart) { this.cart = cart; }
    public BundleRule getBundleRule() { return bundleRule; }
    public void setBundleRule(BundleRule rule) { this.bundleRule = rule; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal amt) { this.discountAmount = amt; }
    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime at) { this.appliedAt = at; }
}