package com.pieceofcake.product_service.product.application;

import com.pieceofcake.product_service.ai.application.AiServiceImpl;
import com.pieceofcake.product_service.ai.dto.in.AiRequestDto;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.common.exception.BaseException;
import com.pieceofcake.product_service.kafka.producer.ProductKafkaProducer;
import com.pieceofcake.product_service.kafka.producer.event.EventType;
import com.pieceofcake.product_service.kafka.producer.event.ProductEvent;
import com.pieceofcake.product_service.kafka.producer.event.ProductImageEvent;
import com.pieceofcake.product_service.kafka.producer.event.ProductStatusEvent;
import com.pieceofcake.product_service.product.dto.in.CreateProductImageRequestDto;
import com.pieceofcake.product_service.product.dto.in.CreateProductRequestDto;
import com.pieceofcake.product_service.product.dto.in.CreateProductRequestDto2;
import com.pieceofcake.product_service.product.dto.in.UpdateProductRequestDto;
import com.pieceofcake.product_service.product.dto.out.GetProductUuidResponseDto;
import com.pieceofcake.product_service.product.entity.Product;
import com.pieceofcake.product_service.product.entity.ProductImage;
import com.pieceofcake.product_service.product.entity.ProductStatus;
import com.pieceofcake.product_service.product.infrastructure.ProductImageRepository;
import com.pieceofcake.product_service.product.infrastructure.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final AiServiceImpl aiService;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductKafkaProducer productKafkaProducer;

    @Transactional
    @Override
    public void createProduct(CreateProductRequestDto2 createProductRequestDto) {
        String productUuid = UUID.randomUUID().toString();

        Product product = productRepository.save(createProductRequestDto.toEntity(productUuid));

        List<ProductImage> productImageList = productImageRepository.saveAll(CreateProductImageRequestDto.of(
                        productUuid, createProductRequestDto.getProductImageRequestDtoList())
                .getProductImageRequestDtoList()
                .stream()
                .map(dto -> dto.toEntity(productUuid))
                .toList());

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                ProductEvent event = ProductEvent.builder()
                        .eventType(EventType.CREATE)
                        .productUuid(product.getProductUuid())
                        .productName(product.getProductName())
                        .aiEstimatedPrice(product.getAiEstimatedPrice())
                        .aiEstimatedDescription(product.getAiEstimatedDescription())
                        .productStatus(product.getProductStatus())
                        .storageLocation(product.getStorageLocation())
                        .purchasePrice(product.getPurchasePrice())
                        .description(product.getDescription())
                        .images(productImageList.stream()
                                .map(ProductImageEvent::from)
                                .toList())
                        .mainCategory(createProductRequestDto.getMainCategory().toEvent())
                        .subCategory(createProductRequestDto.getSubCategory().toEvent())
                        .createdAt(product.getCreatedAt())
                        .updatedAt(product.getUpdatedAt())
                        .build();

                productKafkaProducer.sendCreateProductEvent(event);
            }
        });
    }

    @Transactional
    @Override
    public void createProduct(CreateProductRequestDto createProductRequestDto) {
        String productUuid = UUID.randomUUID().toString();
        String result = aiService.getAnswer(AiRequestDto.of(createProductRequestDto.getDescription(),
                        createProductRequestDto.getProductImageRequestDtoList().get(0).getFileName()))
                .getResult();

        Pattern pattern = Pattern.compile("[\\d,]+\\.");
        Matcher matcher = pattern.matcher(result);

        String price = "";
        String aiDescription = "";

        if (matcher.find()) {
            price = matcher.group(); // ₩11,500,000.
            // 마침표(.) 포함되므로 제거
            price = price.replace(".", "").trim();
            aiDescription = result.substring(matcher.end()).trim().replaceAll("^\"|\"$", ""); // 가격 뒤부터 나머지 설명 추출
        }

        Product product = productRepository.save(createProductRequestDto
                .toEntity(productUuid, Long.parseLong(price.replace(",", "")), aiDescription));

        List<ProductImage> productImageList = productImageRepository.saveAll(CreateProductImageRequestDto.of(
                        productUuid, createProductRequestDto.getProductImageRequestDtoList())
                .getProductImageRequestDtoList()
                .stream()
                .map(dto -> dto.toEntity(productUuid))
                .toList());

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                ProductEvent event = ProductEvent.builder()
                        .eventType(EventType.CREATE)
                        .productUuid(product.getProductUuid())
                        .productName(product.getProductName())
                        .aiEstimatedPrice(product.getAiEstimatedPrice())
                        .aiEstimatedDescription(product.getAiEstimatedDescription())
                        .productStatus(product.getProductStatus())
                        .storageLocation(product.getStorageLocation())
                        .purchasePrice(product.getPurchasePrice())
                        .description(product.getDescription())
                        .images(productImageList.stream()
                                .map(ProductImageEvent::from)
                                .toList())
                        .mainCategory(createProductRequestDto.getMainCategory().toEvent())
                        .subCategory(createProductRequestDto.getSubCategory().toEvent())
                        .createdAt(product.getCreatedAt())
                        .updatedAt(product.getUpdatedAt())
                        .build();

                productKafkaProducer.sendCreateProductEvent(event);
            }
        });
    }

    @Transactional
    @Override
    public void updateProduct(UpdateProductRequestDto updateProductRequestDto) {
        Product product = productRepository.findByProductUuid(updateProductRequestDto.getProductUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        LocalDateTime createdAt = product.getCreatedAt();

        Product newProduct = productRepository.save(updateProductRequestDto.toEntity(product));
        System.out.println("newCreatedAt: " + newProduct.getCreatedAt());

        final List<ProductImage> newProductImageList;
        if (updateProductRequestDto.getProductImageRequestDtoList() == null) {
            newProductImageList = productImageRepository.findAllByProductUuid(updateProductRequestDto.getProductUuid());
        } else {
            productImageRepository.deleteALlByProductUuid(updateProductRequestDto.getProductUuid());
            newProductImageList = productImageRepository.saveAll(CreateProductImageRequestDto.of(
                            updateProductRequestDto.getProductUuid(), updateProductRequestDto.getProductImageRequestDtoList())
                    .getProductImageRequestDtoList()
                    .stream()
                    .map(dto -> dto.toEntity(updateProductRequestDto.getProductUuid()))
                    .toList());
        }

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                ProductEvent event = ProductEvent.builder()
                        .eventType(EventType.UPDATE)
                        .productUuid(newProduct.getProductUuid())
                        .productName(newProduct.getProductName())
                        .aiEstimatedPrice(newProduct.getAiEstimatedPrice())
                        .aiEstimatedDescription(newProduct.getAiEstimatedDescription())
                        .productStatus(product.getProductStatus())
                        .storageLocation(product.getStorageLocation())
                        .purchasePrice(product.getPurchasePrice())
                        .description(newProduct.getDescription())
                        .images(newProductImageList.stream()
                                .map(ProductImageEvent::from)
                                .toList())
                        .mainCategory(updateProductRequestDto.getMainCategory().toEvent())
                        .subCategory(updateProductRequestDto.getSubCategory().toEvent())
                        .createdAt(createdAt)
                        .updatedAt(newProduct.getUpdatedAt())
                        .build();

                productKafkaProducer.sendUpdateProductEvent(event);
            }
        });

    }

    @Override
    public List<GetProductUuidResponseDto> getProductUuidList() {
        return productRepository.findAllByProductStatus(ProductStatus.STORED)
                .stream().map(GetProductUuidResponseDto::from).toList();
    }

    @Transactional
    @Override
    public void deleteProduct(String productUuid) {
        productRepository.softDeleteByProductUuid(productUuid);
        productImageRepository.deleteALlByProductUuid(productUuid);

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                ProductEvent event = ProductEvent.builder()
                        .eventType(EventType.DELETE)
                        .productUuid(productUuid)
                        .build();
                productKafkaProducer.sendDeleteProductEvent(event);
            }
        });
    }

    @Transactional
    @Override
    public void updateProductStatus(String productUuid, ProductStatus productStatus) {
        Product product = productRepository.findByProductUuid(productUuid).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT)
        );

        product.updateProductStatus(productStatus);

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                ProductStatusEvent event = ProductStatusEvent.builder()
                        .productUuid(productUuid)
                        .productStatus(productStatus)
                        .build();
                productKafkaProducer.sendUpdateProductStatusEvent(event);
            }
        });
    }
}
