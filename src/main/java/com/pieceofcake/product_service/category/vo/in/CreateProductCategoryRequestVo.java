package com.pieceofcake.product_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateProductCategoryRequestVo {
    private Integer mainCategoryId;
    private Integer subCategoryId;
    private String productUuid;

    @Builder
    public CreateProductCategoryRequestVo(Integer mainCategoryId, Integer subCategoryId, String productUuid) {
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.productUuid = productUuid;
    }
}
