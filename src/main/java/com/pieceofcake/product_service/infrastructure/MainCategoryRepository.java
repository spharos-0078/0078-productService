package com.pieceofcake.product_service.infrastructure;

import com.pieceofcake.product_service.entity.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Integer> {
}
