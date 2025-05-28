package com.pieceofcake.product_service.infrastructure;

import com.pieceofcake.product_service.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
