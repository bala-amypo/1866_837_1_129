package com.example.demo.repository;

import com.example.demo.entity.BundleRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BundleRuleRepository extends JpaRepository<BundleRule, Long> {
    List<BundleRule> findByActiveTrue();
}