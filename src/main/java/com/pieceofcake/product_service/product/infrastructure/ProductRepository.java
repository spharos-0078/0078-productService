package com.pieceofcake.product_service.product.infrastructure;

import com.pieceofcake.product_service.product.entity.Product;
import com.pieceofcake.product_service.product.entity.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductUuid(String productUuid);
    List<Product> findAllByProductStatus(ProductStatus productStatus);

    @Modifying
    @Query("UPDATE Product p SET p.isDeleted = true WHERE p.productUuid = :productUuid")
    void softDeleteByProductUuid(@Param("productUuid") String productUuid);
}

