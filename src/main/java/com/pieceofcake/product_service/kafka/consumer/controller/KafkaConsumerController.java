package com.pieceofcake.product_service.kafka.consumer.controller;

import com.pieceofcake.product_service.category.application.ProductCategoryServiceImpl;
import com.pieceofcake.product_service.category.dto.in.CreateCategoryNameEventDto;
import com.pieceofcake.product_service.kafka.consumer.event.FundingReadEvent;
import com.pieceofcake.product_service.kafka.consumer.event.PieceReadEvent;
import com.pieceofcake.product_service.kafka.consumer.event.ProductReadEvent;
import com.pieceofcake.product_service.product.application.ProductServiceImpl;
import com.pieceofcake.product_service.product.entity.ProductStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class KafkaConsumerController {

    private final ProductCategoryServiceImpl productCategoryService;
    private final ProductServiceImpl productService;

//    @KafkaListener(topics = "create-product", groupId = "product-enrichment-group", containerFactory = "productEnrichmentListenerFactory")
//    public void consumeCreateProductCategoryEvent(ProductReadEvent productReadEvent) {
//        log.info("get create read topic {}", productReadEvent);
//        productCategoryService.createCategoryNameRead(CreateCategoryNameEventDto.from(productReadEvent));
//    }
//
//    @KafkaListener(topics = "update-product", groupId = "product-enrichment-group", containerFactory = "productEnrichmentListenerFactory")
//    public void consumeUpdateProductCategoryEvent(ProductReadEvent productReadEvent) {
//        log.info("get update read topic {}", productReadEvent);
//        productCategoryService.createCategoryNameRead(CreateCategoryNameEventDto.from(productReadEvent));
//    }

    @KafkaListener(topics = "create-funding", groupId = "product-create-funding-group", containerFactory = "fundingEventListener")
    public void consumeCreateFundingReadEvent(FundingReadEvent event) {
        log.info("Received CREATE FUNDING event: {}", event);
        productService.updateProductStatus(event.getProductUuid(), ProductStatus.FUNDING);
    }

    @KafkaListener(topics = "create-piece-product", groupId = "product-create-funding-group", containerFactory = "pieceEventListener")
    public void consumeCreatePieceReadEvent(PieceReadEvent event) {
        log.info("Received CREATE PIECE PRODUCT event: {}", event);
        productService.updateProductStatus(event.getProductUuid(), ProductStatus.TRADING);
    }
}

