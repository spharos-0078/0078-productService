package com.pieceofcake.product_service.product.application;

import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.common.exception.BaseException;
import com.pieceofcake.product_service.kafka.event.EventType;
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
                        .description(product.getDescription())
                        .images(productImageList.stream()
                                .map(ProductImageEvent::from)
                                .toList())
                        .mainCategoryId(createProductRequestDto.getMainCategoryId())
                        .subCategoryId(createProductRequestDto.getSubCategoryId())
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

        Product newProduct = productRepository.save(updateProductRequestDto.toEntity(product));

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
                        .description(newProduct.getDescription())
                        .images(newProductImageList.stream()
                                .map(ProductImageEvent::from)
                                .toList())
                        .mainCategoryId(updateProductRequestDto.getMainCategoryId())
                        .subCategoryId(updateProductRequestDto.getSubCategoryId())
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
}
