package com.pieceofcake.product_service.product.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetProductUuidResponseVo {
    private String productUuid;

    @Builder
    public GetProductUuidResponseVo(String productUuid) {
        this.productUuid = productUuid;
    }
}
