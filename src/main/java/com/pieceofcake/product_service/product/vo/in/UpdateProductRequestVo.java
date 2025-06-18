package com.pieceofcake.product_service.product.vo.in;

import com.pieceofcake.product_service.category.vo.in.CategoryVo;
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
    private String aiEstimatedDescription;
    private Long purchasePrice;
    private ProductStatus productStatus;
    private String storageLocation;
    private String description;
    private List<ProductImageRequestVo> productImageRequestVoList;
    private CategoryVo mainCategory;
    private CategoryVo subCategory;

    @Builder
    public UpdateProductRequestVo(String productUuid, String productName, Long aiEstimatedPrice, String aiEstimatedDescription,
                                  Long purchasePrice, ProductStatus productStatus, String storageLocation, String description, List<ProductImageRequestVo> productImageRequestVoList, CategoryVo mainCategory, CategoryVo subCategory) {
        this.productUuid = productUuid;
        this.productName = productName;
        this.aiEstimatedPrice = aiEstimatedPrice;
        this.aiEstimatedDescription = aiEstimatedDescription;
        this.purchasePrice = purchasePrice;
        this.productStatus = productStatus;
        this.storageLocation = storageLocation;
        this.description = description;
        this.productImageRequestVoList = productImageRequestVoList;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }
}