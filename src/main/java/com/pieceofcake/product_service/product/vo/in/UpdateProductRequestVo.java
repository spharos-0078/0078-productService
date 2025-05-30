package com.pieceofcake.product_service.product.vo.in;

import com.pieceofcake.product_service.product.entity.ProductStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateProductRequestVo {
    private String productUuid;
    private Long aiEstimatedPrice;
    private Long purchasePrice;
    private ProductStatus productStatus;
    private String storageLocation;
    private String description;

    @Builder
    public UpdateProductRequestVo(String productUuid, Long aiEstimatedPrice, Long purchasePrice, ProductStatus productStatus,
                                  String storageLocation, String description) {
        this.productUuid = productUuid;
        this.aiEstimatedPrice = aiEstimatedPrice;
        this.purchasePrice = purchasePrice;
        this.productStatus = productStatus;
        this.storageLocation = storageLocation;
        this.description = description;
    }
}
