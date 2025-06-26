package com.pieceofcake.product_service.kafka.producer;

import com.pieceofcake.product_service.kafka.producer.event.ProductEvent;
import com.pieceofcake.product_service.kafka.producer.event.ProductStatusEvent;
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
    private final KafkaTemplate<String, ProductStatusEvent> productStatusKafkaTemplate;

    public void sendCreateProductEvent(ProductEvent productEvent) {
        log.info("Sending Create ProductEvent: {}", productEvent);
        CompletableFuture<SendResult<String, ProductEvent>> future =
                productKafkaTemplate.send("create-product", productEvent);
    }

    public void sendUpdateProductEvent(ProductEvent productEvent) {
        log.info("Sending Update ProductEvent: {}", productEvent);
        CompletableFuture<SendResult<String, ProductEvent>> future =
                productKafkaTemplate.send("update-product", productEvent);
    }

    public void sendDeleteProductEvent(ProductEvent productEvent) {
        log.info("Sending Delete ProductEvent: {}", productEvent);
        CompletableFuture<SendResult<String, ProductEvent>> future =
                productKafkaTemplate.send("delete-product", productEvent);
    }

    public void sendUpdateProductStatusEvent(ProductStatusEvent productStatusEvent) {
        log.info("Sending Update ProductStatusEvent: {}", productStatusEvent);
        CompletableFuture<SendResult<String, ProductStatusEvent>> future =
                productStatusKafkaTemplate.send("update-product-status", productStatusEvent);
    }
}