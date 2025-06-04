package com.pieceofcake.product_service.kafka.event;

import com.pieceofcake.product_service.product.entity.ProductStatus;
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
    private List<ProductImageEvent> images;
    private Integer mainCategoryId;
    private Integer subCategoryId;
}
