package com.pieceofcake.product_service.product.vo.in;

import com.pieceofcake.product_service.category.vo.in.CategoryVo;
import com.pieceofcake.product_service.product.entity.ProductStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateProductRequestVo {
    private String productName;
    private Long aiEstimatedPrice;
    private Long purchasePrice;
    private ProductStatus status;
    private String storageLocation;
    private String description;
    private List<ProductImageRequestVo> productImageRequestVoList;
    private CategoryVo mainCategory;
    private CategoryVo subCategory;

    @Builder
    public CreateProductRequestVo(String productName, Long aiEstimatedPrice, Long purchasePrice, ProductStatus status,
                                  String storageLocation, String description, List<ProductImageRequestVo> productImageRequestVoList, CategoryVo mainCategory, CategoryVo subCategory) {
        this.productName = productName;
        this.aiEstimatedPrice = aiEstimatedPrice;
        this.purchasePrice = purchasePrice;
        this.status = status;
        this.storageLocation = storageLocation;
        this.description = description;
        this.productImageRequestVoList = productImageRequestVoList;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }
}
