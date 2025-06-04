package com.pieceofcake.product_service.product.application;

import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.common.exception.BaseException;
import com.pieceofcake.product_service.kafka.event.ProductEvent;
import com.pieceofcake.product_service.kafka.event.ProductImageEvent;
import com.pieceofcake.product_service.kafka.producer.ProductKafkaProducer;
import com.pieceofcake.product_service.product.dto.in.CreateProductImageRequestDto;
import com.pieceofcake.product_service.product.dto.in.CreateProductRequestDto;
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

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductKafkaProducer productKafkaProducer;

    @Transactional
    @Override
    public void createProduct(CreateProductRequestDto createProductRequestDto) {
        String productUuid = UUID.randomUUID().toString();
        Product product = productRepository.save(createProductRequestDto.toEntity(productUuid));

        // 단순 저장이기 때문에 repo호출
        List<ProductImage> productImageList = productImageRepository.saveAll(CreateProductImageRequestDto.of( // 영상
                        productUuid, createProductRequestDto.getProductImageRequestDtoList())
                .getProductImageRequestDtoList()
                .stream()
                .map(dto -> dto.toEntity(productUuid))
                .toList());

        // 트랜잭션 커밋 후 Kafka 메시지 발행
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                ProductEvent event = ProductEvent.builder()
                        .productUuid(product.getProductUuid())
                        .productName(product.getProductName())
                        .aiEstimatedPrice(product.getAiEstimatedPrice())
                        .purchasePrice(product.getPurchasePrice())
                        .productStatus(product.getProductStatus())
                        .storageLocation(product.getStorageLocation())
                        .description(product.getDescription())
                        .images(productImageList.stream()
                                .map(ProductImageEvent::from)
                                .toList())
                        .mainCategoryId(createProductRequestDto.getMainCategoryId())
                        .subCategoryId(createProductRequestDto.getSubCategoryId())
                        .build();

                productKafkaProducer.sendProductEvent(event);
            }
        });
    }

    @Transactional
    @Override
    public void updateProduct(UpdateProductRequestDto updateProductRequestDto) {
        Product product = productRepository.findByProductUuid(updateProductRequestDto.getProductUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));

        productRepository.save(updateProductRequestDto.toEntity(product));
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
    }
}
