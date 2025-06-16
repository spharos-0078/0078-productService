package com.pieceofcake.product_service.kafka.consumer.controller;

import com.pieceofcake.product_service.category.application.ProductCategoryServiceImpl;
import com.pieceofcake.product_service.category.dto.in.CreateCategoryNameEventDto;
import com.pieceofcake.product_service.kafka.consumer.event.ProductReadEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class KafkaConsumerController {

    private final ProductCategoryServiceImpl productCategoryService;

    @KafkaListener(topics = "create-product", groupId = "product-enrichment-group", containerFactory = "productEnrichmentListenerFactory")
    public void consumeCreateProductCategoryEvent(ProductReadEvent productReadEvent) {
        log.info("get create read topic {}", productReadEvent);
        productCategoryService.createCategoryNameRead(CreateCategoryNameEventDto.from(productReadEvent));
    }

    @KafkaListener(topics = "update-product", groupId = "product-enrichment-group", containerFactory = "productEnrichmentListenerFactory")
    public void consumeUpdateProductCategoryEvent(ProductReadEvent productReadEvent) {
        log.info("get update read topic {}", productReadEvent);
        productCategoryService.createCategoryNameRead(CreateCategoryNameEventDto.from(productReadEvent));
    }
}

