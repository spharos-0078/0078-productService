package com.pieceofcake.product_service.product.vo.in;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateProductImageRequestVo {
    private String productUuid;
    private List<ProductImageRequestVo> productImageRequestVoList;

    @Builder
    public CreateProductImageRequestVo(String productUuid, List<ProductImageRequestVo> productImageRequestVoList) {
        this.productUuid = productUuid;
        this.productImageRequestVoList = productImageRequestVoList;
    }
}
