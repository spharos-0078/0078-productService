package com.pieceofcake.product_service.category.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "main_category_id", nullable = false)
    private Integer mainCategoryId;

    @Column(name = "sub_category_id", nullable = false)
    private Integer subCategoryId;

    @Column(name = "product_uuid", nullable = false)
    private String productUuid;

    @Builder
    public ProductCategory(Integer id, Integer mainCategoryId, Integer subCategoryId, String productUuid) {
        this.id = id;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.productUuid = productUuid;
    }
}
