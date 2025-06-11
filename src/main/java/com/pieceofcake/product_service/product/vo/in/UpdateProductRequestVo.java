package com.pieceofcake.product_service.product.vo.in;

import com.pieceofcake.product_service.product.dto.in.ProductImageRequestDto;
import com.pieceofcake.product_service.product.entity.ProductStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class UpdateProductRequestVo {
    private String productUuid;
    private String productName;
    private Long aiEstimatedPrice;
    private Long purchasePrice;
    private ProductStatus productStatus;
    private String storageLocation;
    private String description;
    private List<ProductImageRequestVo> productImageRequestVoList;
    private Integer mainCategoryId;
    private Integer subCategoryId;

    @Builder
    public UpdateProductRequestVo(String productUuid, String productName, Long aiEstimatedPrice, Long purchasePrice, ProductStatus productStatus,
                                  String storageLocation, String description, List<ProductImageRequestVo> productImageRequestVoList, Integer mainCategoryId, Integer subCategoryId) {
        this.productUuid = productUuid;
        this.productName = productName;
        this.aiEstimatedPrice = aiEstimatedPrice;
        this.purchasePrice = purchasePrice;
        this.productStatus = productStatus;
        this.storageLocation = storageLocation;
        this.description = description;
        this.productImageRequestVoList = productImageRequestVoList;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
    }
}