package com.adepuu.freshGoodiesBackend.cart.entity.repository;

import com.adepuu.freshGoodiesBackend.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
