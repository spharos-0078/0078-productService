package com.pieceofcake.product_service.category.infrastructure;

import com.pieceofcake.product_service.category.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    List<SubCategory> findAllByMainCategoryId(Integer mainCategoryId);
}
