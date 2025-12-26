package com.example.demo.repository;

import com.example.demo.model.DiscountApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiscountApplicationRepository extends JpaRepository<DiscountApplication, Long> {
    void deleteByCartId(Long cartId); [cite: 188]
    List<DiscountApplication> findByCartId(Long cartId); [cite: 189]
}