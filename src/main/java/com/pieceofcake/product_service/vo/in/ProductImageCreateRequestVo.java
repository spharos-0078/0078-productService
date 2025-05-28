package com.pieceofcake.product_service.vo.in;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductImageCreateRequestVo {
    private String productUuid;
    private List<ProductImageRequestVo> productImageRequestVoList;

    @Builder
    public ProductImageCreateRequestVo(String productUuid, List<ProductImageRequestVo> productImageRequestVoList) {
        this.productUuid = productUuid;
        this.productImageRequestVoList = productImageRequestVoList;
    }
}
