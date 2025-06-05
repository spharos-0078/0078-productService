package com.pieceofcake.product_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateSubCategoryRequestVo {
    private Integer id;
    private String categoryName;
    private Integer mainCategoryId;

    @Builder
    public UpdateSubCategoryRequestVo(Integer id, String categoryName, Integer mainCategoryId) {
        this.id = id;
        this.categoryName = categoryName;
        this.mainCategoryId = mainCategoryId;
    }
}
