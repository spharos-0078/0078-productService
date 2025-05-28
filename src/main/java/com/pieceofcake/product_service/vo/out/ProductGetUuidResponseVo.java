package com.pieceofcake.product_service.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductGetUuidResponseVo {
    private String productUuid;

    @Builder
    public ProductGetUuidResponseVo(String productUuid) {
        this.productUuid = productUuid;
    }
}
