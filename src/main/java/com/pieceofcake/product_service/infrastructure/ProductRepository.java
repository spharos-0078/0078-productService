package com.pieceofcake.product_service.infrastructure;

import com.pieceofcake.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

