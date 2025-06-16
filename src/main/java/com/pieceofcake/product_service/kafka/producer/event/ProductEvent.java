package com.pieceofcake.product_service.kafka.producer.event;

import lombok.*;

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
    private String description;
    private List<ProductImageEvent> images;
    private CategoryEvent mainCategory;
    private CategoryEvent subCategory;
}
