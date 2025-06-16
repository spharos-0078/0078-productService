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

    public void sendCreateProductEvent(ProductEvent productEvent) {
        log.info("Sending Create ProductEvent: {}", productEvent);
        CompletableFuture<SendResult<String, ProductEvent>> future =
                productKafkaTemplate.send("create-product-send-read", productEvent);
    }

    public void sendUpdateProductEvent(ProductEvent productEvent) {
        log.info("Sending Update ProductEvent: {}", productEvent);
        CompletableFuture<SendResult<String, ProductEvent>> future =
                productKafkaTemplate.send("update-product-send-read", productEvent);
    }

    public void sendDeleteProductEvent(ProductEvent productEvent) {
        log.info("Sending Delete ProductEvent: {}", productEvent);
        CompletableFuture<SendResult<String, ProductEvent>> future =
                productKafkaTemplate.send("delete-product-send-read", productEvent);
    }
}
