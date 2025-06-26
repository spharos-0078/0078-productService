package com.pieceofcake.product_service.kafka.consumer.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class PieceReadEvent {
    String productUuid;
    String pieceProductUuid;
    Boolean isTrading;
    String status;
}
