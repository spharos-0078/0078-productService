package com.pieceofcake.product_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateMainCategoryRequestVo {
    private String name;

    @Builder
    public CreateMainCategoryRequestVo(String name) {
        this.name = name;
    }
}
