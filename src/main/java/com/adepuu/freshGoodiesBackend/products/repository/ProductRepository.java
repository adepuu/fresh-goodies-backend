package com.adepuu.freshGoodiesBackend.products.repository;

import com.adepuu.freshGoodiesBackend.products.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{}
