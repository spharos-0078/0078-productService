package com.pieceofcake.product_service.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MainCategoryCreateRequestVo {
    private String name;

    @Builder
    public MainCategoryCreateRequestVo(String name) {
        this.name = name;
    }
}
