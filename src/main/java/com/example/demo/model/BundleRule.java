package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class BundleRule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ruleName;
    private String requiredProductIds; // CSV string [cite: 159, 162]
    private Double discountPercentage;
    private Boolean active = true;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }
    public String getRequiredProductIds() { return requiredProductIds; }
    public void setRequiredProductIds(String ids) { this.requiredProductIds = ids; }
    public Double getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(Double pct) { this.discountPercentage = pct; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}