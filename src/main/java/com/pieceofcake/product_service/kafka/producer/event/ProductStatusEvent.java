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
public class ProductStatusEvent {
    private String productUuid;
    private ProductStatus productStatus;
}
