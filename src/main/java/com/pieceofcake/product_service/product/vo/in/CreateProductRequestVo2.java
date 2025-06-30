package com.pieceofcake.product_service.product.vo.in;

import com.pieceofcake.product_service.category.vo.in.CategoryVo;
import com.pieceofcake.product_service.product.entity.ProductStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateProductRequestVo2 {
    private String productName;
    private Long purchasePrice;
    private ProductStatus status;
    private String storageLocation;
    private String description;
    private List<ProductImageRequestVo> productImageRequestVoList;
    private CategoryVo mainCategory;
    private CategoryVo subCategory;

    // AI 사용시 주석처리하기
    private Long price;
    private String aiDescription;

    @Builder
    public CreateProductRequestVo2(String productName, Long purchasePrice, ProductStatus status, String storageLocation,
                                  String description, List<ProductImageRequestVo> productImageRequestVoList, CategoryVo mainCategory, CategoryVo subCategory, Long aiEstimatedPrice, String aiEstimatedDescription) {
        this.productName = productName;
        this.purchasePrice = purchasePrice;
        this.status = status;
        this.storageLocation = storageLocation;
        this.description = description;
        this.productImageRequestVoList = productImageRequestVoList;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.price = aiEstimatedPrice;
        this.aiDescription = aiEstimatedDescription;
    }
}
