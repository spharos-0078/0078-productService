package com.pieceofcake.product_service.kafka.event;

import com.pieceofcake.product_service.product.entity.ProductStatus;
import com.pieceofcake.product_service.kafka.dto.ProductImageDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEvent {
    private String productUuid;
    private String productName;
    private Long aiEstimatedPrice;
    private Long purchasePrice;
    private ProductStatus productStatus;
    private String storageLocation;
    private String description;
    private List<ProductImageDto> images;
    private Long mainCategoryId;
    private Long subCategoryId;
}
