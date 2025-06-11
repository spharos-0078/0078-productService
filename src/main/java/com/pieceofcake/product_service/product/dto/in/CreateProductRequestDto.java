package com.pieceofcake.product_service.product.dto.in;

import com.pieceofcake.product_service.product.entity.Product;
import com.pieceofcake.product_service.product.entity.ProductStatus;
import com.pieceofcake.product_service.product.vo.in.CreateProductRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateProductRequestDto {
    private String productName;
    private Long aiEstimatedPrice;
    private Long purchasePrice;
    private ProductStatus status;
    private String storageLocation;
    private String description;
    private List<ProductImageRequestDto> productImageRequestDtoList;
    private Integer mainCategoryId;
    private Integer subCategoryId;

    @Builder
    public CreateProductRequestDto(String productName, Long aiEstimatedPrice, Long purchasePrice, ProductStatus status,
                                   String storageLocation, String description, List<ProductImageRequestDto> productImageRequestDtoList,
                                   Integer mainCategoryId, Integer subCategoryId) {
        this.productName = productName;
        this.aiEstimatedPrice = aiEstimatedPrice;
        this.purchasePrice = purchasePrice;
        this.status = status;
        this.storageLocation = storageLocation;
        this.description = description;
        this.productImageRequestDtoList = productImageRequestDtoList;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
    }

    public static CreateProductRequestDto from(CreateProductRequestVo vo){
        return CreateProductRequestDto.builder()
                .productName(vo.getProductName())
                .aiEstimatedPrice(vo.getAiEstimatedPrice())
                .purchasePrice(vo.getPurchasePrice())
                .status(vo.getStatus())
                .storageLocation(vo.getStorageLocation())
                .description(vo.getDescription())
                .productImageRequestDtoList(vo.getProductImageRequestVoList().stream()
                        .map(ProductImageRequestDto::from).toList())
                .mainCategoryId(vo.getMainCategoryId())
                .subCategoryId(vo.getSubCategoryId())
                .build();
    }


    public Product toEntity(String productUuid){
        return Product.builder()
                .productUuid(productUuid)
                .productName(productName)
                .aiEstimatedPrice(aiEstimatedPrice)
                .purchasePrice(purchasePrice)
                .productStatus(status)
                .storageLocation(storageLocation)
                .description(description)
                .build();
    }
}
