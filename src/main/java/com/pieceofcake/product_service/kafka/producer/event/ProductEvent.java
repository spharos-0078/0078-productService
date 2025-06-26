package com.pieceofcake.product_service.kafka.producer.event;

import com.pieceofcake.product_service.product.entity.ProductStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEvent {
    private EventType eventType;
    private String productUuid;
    private String productName;
    private Long aiEstimatedPrice;
    private String aiEstimatedDescription;
    private String description;
    private Long purchasePrice;
    private ProductStatus productStatus;
    private String storageLocation;
    private List<ProductImageEvent> images;
    private CategoryEvent mainCategory;
    private CategoryEvent subCategory;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
