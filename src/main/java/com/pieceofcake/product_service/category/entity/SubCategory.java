package com.pieceofcake.product_service.category.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "main_category_id", nullable = false)
    private Integer mainCategoryId;

    @Column(name = "sub_category_name", nullable = false, length = 50)
    private String subCategoryName;

    @Builder
    public SubCategory(Integer id, Integer mainCategoryId, String subCategoryName) {
        this.id = id;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryName = subCategoryName;
    }
}
