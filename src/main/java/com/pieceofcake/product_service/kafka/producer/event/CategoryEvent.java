package com.pieceofcake.product_service.kafka.producer.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEvent {
    private Integer categoryId;
    private String categoryName;
}
