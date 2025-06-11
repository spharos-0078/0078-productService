package com.pieceofcake.product_service.product.application;

import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.common.exception.BaseException;
import com.pieceofcake.product_service.kafka.event.ProductEvent;
import com.pieceofcake.product_service.kafka.event.ProductImageEvent;
import com.pieceofcake.product_service.kafka.producer.ProductKafkaProducer;
import com.pieceofcake.product_service.product.dto.in.CreateProductImageRequestDto;
import com.pieceofcake.product_service.product.dto.in.CreateProductRequestDto;
import com.pieceofcake.product_service.product.dto.in.UpdateProductInfoRequestDto;
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

import java.util.ArrayList;
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

        // 트랜잭션 커밋 후 Kafka 메시지 발행 -> commit 완료 후 kafka가 죽는다면 문제 발생 -> 보상로직 필요
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                ProductEvent event = ProductEvent.builder()
                        .eventType("CREATE")
                        .productUuid(product.getProductUuid())
                        .productName(product.getProductName())
                        .aiEstimatedPrice(product.getAiEstimatedPrice())
                        .description(product.getDescription())
                        .images(productImageList.stream()
                                .map(ProductImageEvent::from)
                                .toList())
                        .mainCategoryId(createProductRequestDto.getMainCategoryId()) // category-topic에서 상품 consum -> 카테고리 pro -> 카테고리 이름 consum
                        .subCategoryId(createProductRequestDto.getSubCategoryId())
                        .build();

                productKafkaProducer.sendProductEvent(event);
            }
        });
    }

//    @Transactional
//    @Override
//    public void updateProduct(UpdateProductInfoRequestDto updateProductInfoRequestDto) {
//        Product product = productRepository.findByProductUuid(updateProductInfoRequestDto.getProductUuid())
//                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
//
//        productRepository.save(updateProductInfoRequestDto.toEntity(product));
//    }

    @Transactional
    @Override
    public void updateProduct(UpdateProductRequestDto updateProductRequestDto) {
        Product product = productRepository.findByProductUuid(updateProductRequestDto.getProductUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));

        Product newProduct = productRepository.save(updateProductRequestDto.toEntity(product));

        final List<ProductImage> newProductImageList;
        if(updateProductRequestDto.getProductImageRequestDtoList() == null){
            newProductImageList = productImageRepository.findAllByProductUuid(updateProductRequestDto.getProductUuid());
        }
        else{
            productImageRepository.deleteALlByProductUuid(updateProductRequestDto.getProductUuid());
            newProductImageList = productImageRepository.saveAll(CreateProductImageRequestDto.of( // 영상
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
                        .eventType("UPDATE")
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

                productKafkaProducer.sendProductEvent(event);
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
    }
}
