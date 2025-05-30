package com.pieceofcake.product_service.category.infrastructure;

import com.pieceofcake.product_service.category.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
}
