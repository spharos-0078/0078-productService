package com.pieceofcake.product_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateSubCategoryRequestVo {
    private Integer mainCategoryId;
    private String name;

    @Builder
    public CreateSubCategoryRequestVo(Integer mainCategoryId, String name) {
        this.mainCategoryId = mainCategoryId;
        this.name = name;
    }
}
