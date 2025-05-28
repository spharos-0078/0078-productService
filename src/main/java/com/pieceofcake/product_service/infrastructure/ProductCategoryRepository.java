package com.pieceofcake.product_service.infrastructure;

import com.pieceofcake.product_service.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
