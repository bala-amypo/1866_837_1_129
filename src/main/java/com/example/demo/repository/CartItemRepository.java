package com.example.demo.repository;

import com.example.demo.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId); [cite: 185]
    List<CartItem> findByCartId(Long cartId); [cite: 186]
    
    @Query("select ci from CartItem ci where ci.cart.id = :cartId and ci.quantity >= :minQty")
    List<CartItem> findByCartIdAndMinQuantity(@Param("cartId") Long cartId, @Param("minQty") Integer minQty); [cite: 187]
}