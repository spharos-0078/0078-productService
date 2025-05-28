package com.pieceofcake.product_service.dto.in;

import com.pieceofcake.product_service.entity.Product;
import com.pieceofcake.product_service.entity.ProductStatus;
import com.pieceofcake.product_service.vo.in.ProductCreateRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductCreateRequestDto {
    private String productName;
    private Long aiEstimatedPrice;
    private Long purchasePrice;
    private ProductStatus status;
    private String storageLocation;
    private String description;
    private List<ProductImageRequestDto> productImageRequestDtoList;

    @Builder
    public ProductCreateRequestDto(String productName, Long aiEstimatedPrice, Long purchasePrice, ProductStatus status,
                                   String storageLocation, String description, List<ProductImageRequestDto> productImageRequestDtoList) {
        this.productName = productName;
        this.aiEstimatedPrice = aiEstimatedPrice;
        this.purchasePrice = purchasePrice;
        this.status = status;
        this.storageLocation = storageLocation;
        this.description = description;
        this.productImageRequestDtoList = productImageRequestDtoList;
    }

    public static ProductCreateRequestDto from(ProductCreateRequestVo vo){
        return ProductCreateRequestDto.builder()
                .productName(vo.getProductName())
                .aiEstimatedPrice(vo.getAiEstimatedPrice())
                .purchasePrice(vo.getPurchasePrice())
                .status(vo.getStatus())
                .storageLocation(vo.getStorageLocation())
                .description(vo.getDescription())
                .productImageRequestDtoList(vo.getProductImageRequestVoList().stream()
                        .map(ProductImageRequestDto::from).toList())
                .build();
    }


    public Product toEntity(String productUuid){
        return Product.builder()
                .productUuid(productUuid)
                .productName(this.productName)
                .aiEstimatedPrice(this.aiEstimatedPrice)
                .purchasePrice(this.purchasePrice)
                .productStatus(this.status)
                .storageLocation(this.storageLocation)
                .description(this.description)
                .build();
    }
}
