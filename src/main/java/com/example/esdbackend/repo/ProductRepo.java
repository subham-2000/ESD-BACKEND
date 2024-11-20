package com.example.esdbackend.repo;


import com.example.esdbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax ORDER BY p.price DESC LIMIT 2")
    List<Product> findByValue(@Param("priceMin")Double priceMin, @Param("priceMax")Double priceMax);

}
