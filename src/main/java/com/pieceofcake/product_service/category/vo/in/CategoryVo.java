package com.pieceofcake.product_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryVo {
    private Integer categoryId;
    private String categoryName;

    @Builder
    public CategoryVo(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
