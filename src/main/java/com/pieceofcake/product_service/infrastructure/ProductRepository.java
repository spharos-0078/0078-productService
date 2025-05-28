package com.pieceofcake.product_service.infrastructure;

import com.pieceofcake.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductUuid(String productUuid);
}

