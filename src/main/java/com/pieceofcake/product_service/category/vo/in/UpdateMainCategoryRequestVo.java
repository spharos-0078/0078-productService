package com.pieceofcake.product_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateMainCategoryRequestVo {
    private Integer id;
    private String categoryName;

    @Builder
    public UpdateMainCategoryRequestVo(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
