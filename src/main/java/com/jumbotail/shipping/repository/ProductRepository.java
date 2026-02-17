package com.jumbotail.shipping.repository;

import com.jumbotail.shipping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findById(String id);
    
    List<Product> findBySellerId(String sellerId);
    
    @Query("SELECT p FROM Product p WHERE p.sellerId = :sellerId")
    List<Product> findProductsBySeller(@Param("sellerId") String sellerId);
}
