package com.pieceofcake.product_service.product.dto.in;

import com.pieceofcake.product_service.product.entity.Product;
import com.pieceofcake.product_service.product.entity.ProductStatus;
import com.pieceofcake.product_service.product.vo.in.UpdateProductRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateProductRequestDto {
    private String productUuid;
    private Long aiEstimatedPrice;
    private Long purchasePrice;
    private ProductStatus productStatus;
    private String storageLocation;
    private String description;

    @Builder
    public UpdateProductRequestDto(String productUuid, Long aiEstimatedPrice, Long purchasePrice, ProductStatus productStatus,
                                   String storageLocation, String description) {
        this.productUuid = productUuid;
        this.aiEstimatedPrice = aiEstimatedPrice;
        this.purchasePrice = purchasePrice;
        this.productStatus = productStatus;
        this.storageLocation = storageLocation;
        this.description = description;
    }

    public static UpdateProductRequestDto from(UpdateProductRequestVo vo) {
        return UpdateProductRequestDto.builder()
                .productUuid(vo.getProductUuid())
                .aiEstimatedPrice(vo.getAiEstimatedPrice())
                .purchasePrice(vo.getPurchasePrice())
                .productStatus(vo.getProductStatus())
                .storageLocation(vo.getStorageLocation())
                .description(vo.getDescription())
                .build();
    }

    public Product toEntity(Product product){
        return Product.builder()
                .productUuid(product.getProductUuid())
                .aiEstimatedPrice(aiEstimatedPrice == null ? product.getAiEstimatedPrice() : aiEstimatedPrice)
                .purchasePrice(purchasePrice == null ? product.getPurchasePrice() : purchasePrice)
                .productStatus(productStatus == null ? product.getProductStatus() : productStatus)
                .storageLocation(storageLocation == null ? product.getStorageLocation() : storageLocation)
                .description(description == null ? product.getDescription() : description)
                .build();
    }
}
