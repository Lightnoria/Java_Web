package com.example.cosmocats.repository;

import com.example.cosmocats.model.Product;
import com.example.cosmocats.projection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p.name AS name, p.price AS price FROM Product p WHERE p.name LIKE %?1%")
    List<ProductProjection> findByNameContaining(String name);

    // Optional custom query to search for the most popular products
    @Query("SELECT p.name AS name, p.price AS price, COUNT(o) AS orderCount FROM Product p " +
            "JOIN Order o ON o.products MEMBER OF p WHERE o.id IS NOT NULL " +
            "GROUP BY p.name ORDER BY orderCount DESC")
    List<Object[]> findMostPopularProducts();
}
