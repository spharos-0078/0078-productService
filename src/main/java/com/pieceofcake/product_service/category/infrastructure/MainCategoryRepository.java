package com.pieceofcake.product_service.category.infrastructure;

import com.pieceofcake.product_service.category.entity.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Integer> {
}
