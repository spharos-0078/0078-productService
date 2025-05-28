package com.pieceofcake.product_service.infrastructure;

import com.pieceofcake.product_service.entity.Product;
import com.pieceofcake.product_service.entity.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductUuid(String productUuid);
    List<Product> findAllByProductStatus(ProductStatus productStatus);
}

