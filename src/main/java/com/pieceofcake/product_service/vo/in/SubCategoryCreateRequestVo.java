package com.pieceofcake.product_service.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SubCategoryCreateRequestVo {
    private Integer mainCategoryId;
    private String name;

    @Builder
    public SubCategoryCreateRequestVo(Integer mainCategoryId, String name) {
        this.mainCategoryId = mainCategoryId;
        this.name = name;
    }
}
