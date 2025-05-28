package com.pieceofcake.product_service.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductCategoryCreateRequestVo {
    private Integer mainCategoryId;
    private Integer subCategoryId;
    private String productUuid;

    @Builder
    public ProductCategoryCreateRequestVo(Integer mainCategoryId, Integer subCategoryId, String productUuid) {
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.productUuid = productUuid;
    }
}
