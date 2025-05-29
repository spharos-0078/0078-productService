package com.pieceofcake.product_service.vo.in;

import com.pieceofcake.product_service.entity.ProductStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductCreateRequestVo {
    private String productName;
    private Long aiEstimatedPrice;
    private Long purchasePrice;
    private ProductStatus status;
    private String storageLocation;
    private String description;
    private List<ProductImageRequestVo> productImageRequestVoList;
    private Integer mainCategoryId;
    private Integer subCategoryId;

    @Builder
    public ProductCreateRequestVo(String productName, Long aiEstimatedPrice, Long purchasePrice, ProductStatus status,
                                  String storageLocation, String description, List<ProductImageRequestVo> productImageRequestVoList,
                                  Integer mainCategoryId, Integer subCategoryId) {
        this.productName = productName;
        this.aiEstimatedPrice = aiEstimatedPrice;
        this.purchasePrice = purchasePrice;
        this.status = status;
        this.storageLocation = storageLocation;
        this.description = description;
        this.productImageRequestVoList = productImageRequestVoList;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
    }
}
