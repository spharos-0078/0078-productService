package com.pieceofcake.product_service.kafka.producer.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryNameEvent {
    private String productUuid;
    private String mainCategoryName;
    private String subCategoryName;
}

