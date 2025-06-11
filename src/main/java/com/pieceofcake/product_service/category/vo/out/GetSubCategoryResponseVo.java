package com.pieceofcake.product_service.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetSubCategoryResponseVo {
    private Integer id;
    private String categoryName;

    @Builder
    public GetSubCategoryResponseVo(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
