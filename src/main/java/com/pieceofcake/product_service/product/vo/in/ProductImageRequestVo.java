package com.pieceofcake.product_service.product.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductImageRequestVo {
    private Integer imageIndex;
    private Boolean isThumbnail;
    private String fileName;

    @Builder
    public ProductImageRequestVo(Integer imageIndex, Boolean isThumbnail, String fileName) {
        this.imageIndex = imageIndex;
        this.isThumbnail = isThumbnail;
        this.fileName = fileName;
    }
}