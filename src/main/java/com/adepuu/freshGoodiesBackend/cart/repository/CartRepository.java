package com.adepuu.freshGoodiesBackend.cart.repository;

import com.adepuu.freshGoodiesBackend.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.id = :cartId AND c.deletedAt IS NULL")
    Cart findActiveCartById(Long cartId);
}
