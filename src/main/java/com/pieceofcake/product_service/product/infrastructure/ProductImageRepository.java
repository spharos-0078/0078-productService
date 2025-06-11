package com.pieceofcake.product_service.product.infrastructure;

import com.pieceofcake.product_service.product.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findAllByProductUuid(String productUuid);
    void deleteALlByProductUuid(String productUuid);
}
