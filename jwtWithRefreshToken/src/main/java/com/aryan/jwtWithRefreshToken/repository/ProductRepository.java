package com.aryan.jwtWithRefreshToken.repository;

import com.aryan.jwtWithRefreshToken.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}