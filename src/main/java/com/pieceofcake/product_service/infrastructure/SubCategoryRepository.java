package com.pieceofcake.product_service.infrastructure;

import com.pieceofcake.product_service.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
}
