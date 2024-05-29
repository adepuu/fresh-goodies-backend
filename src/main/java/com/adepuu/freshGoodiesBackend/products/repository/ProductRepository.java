package com.adepuu.freshGoodiesBackend.products.repository;

import com.adepuu.freshGoodiesBackend.products.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
  @Modifying
  @Transactional
  @Query("UPDATE Product p SET p.deletedAt = CURRENT_TIMESTAMP WHERE p.id = :id")
  void softDeleteById(Long id);

  @Modifying
  @Transactional
  @Query("UPDATE Metadata m SET m.deletedAt = CURRENT_TIMESTAMP WHERE m.id = (SELECT p.metadata.id FROM Product p WHERE p.id = :id)")
  void softDeleteMetadataByProductId(Long id);
}
