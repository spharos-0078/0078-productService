package com.pieceofcake.product_service.kafka.producer;

import com.pieceofcake.product_service.kafka.producer.event.CategoryNameEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryKafkaProducer {

    private final KafkaTemplate<String, CategoryNameEvent> categoryKafkaTemplate;

    public void sendCategoryNameEvent(CategoryNameEvent categoryNameEvent) {
        log.info("Sending CategoryNameEvent: {}", categoryNameEvent);
        CompletableFuture<SendResult<String, CategoryNameEvent>> future =
                categoryKafkaTemplate.send("get-category-name", categoryNameEvent);
    }
}
