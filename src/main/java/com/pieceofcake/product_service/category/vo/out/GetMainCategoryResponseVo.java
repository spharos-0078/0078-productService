package com.pieceofcake.product_service.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetMainCategoryResponseVo {
    private Integer id;
    private String categoryName;

    @Builder
    public GetMainCategoryResponseVo(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
