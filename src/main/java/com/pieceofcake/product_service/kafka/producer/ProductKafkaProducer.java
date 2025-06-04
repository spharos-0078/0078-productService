package com.pieceofcake.product_service.kafka.producer;

import com.pieceofcake.product_service.kafka.event.ProductEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductKafkaProducer {

    private final KafkaTemplate<String, ProductEvent> productKafkaTemplate;

    public void sendProductEvent(ProductEvent productEvent) {
        log.info("Sending ProductEvent: {}", productEvent);
        CompletableFuture<SendResult<String, ProductEvent>> future =
                productKafkaTemplate.send("create-product-send-read", productEvent);
    }
}
