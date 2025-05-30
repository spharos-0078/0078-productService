package com.pieceofcake.product_service.category.infrastructure;

import com.pieceofcake.product_service.category.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    ProductCategory findByProductUuid(String productUuid);
}
