package com.pieceofcake.product_service.product.application;

import com.pieceofcake.product_service.category.application.ProductCategoryServiceImpl;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.common.exception.BaseException;
import com.pieceofcake.product_service.category.dto.in.CreateProductCategoryRequestDto;
import com.pieceofcake.product_service.product.dto.in.CreateProductRequestDto;
import com.pieceofcake.product_service.product.dto.in.CreateProductImageRequestDto;
import com.pieceofcake.product_service.product.dto.in.UpdateProductRequestDto;
import com.pieceofcake.product_service.product.dto.out.GetProductUuidResponseDto;
import com.pieceofcake.product_service.product.entity.Product;
import com.pieceofcake.product_service.product.entity.ProductStatus;
import com.pieceofcake.product_service.product.infrastructure.ProductImageRepository;
import com.pieceofcake.product_service.product.infrastructure.ProductRepository;
import com.pieceofcake.product_service.kafka.event.ProductEvent;
import com.pieceofcake.product_service.kafka.factory.ProductEventFactory;
import com.pieceofcake.product_service.kafka.producer.ProductKafkaProducer;
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
    private final ProductImageServiceImpl productImageService;
    private final ProductCategoryServiceImpl productCategoryService;
    private final ProductEventFactory productEventFactory;
    private final ProductKafkaProducer productKafkaProducer;

    @Transactional
    @Override
    public void createProduct(CreateProductRequestDto createProductRequestDto) {
        String productUuid = UUID.randomUUID().toString();
        Product product = productRepository.save(createProductRequestDto.toEntity(productUuid));

        // 단순 저장이기 때문에 repo호출(+ 보상로직이 없음)
//        productImageService.createProductImage(CreateProductImageRequestDto.of( // 영상
//                productUuid, createProductRequestDto.getProductImageRequestDtoList()));
        productImageRepository.saveAll(CreateProductImageRequestDto.of( // 영상
                productUuid, createProductRequestDto.getProductImageRequestDtoList())
                        .getProductImageRequestDtoList()
                .stream()
                .map(dto -> dto.toEntity(productUuid))
                .toList());

//        productCategoryService.createProductCategory( // 조회하기 위한 테이블이기 떄문에 read table
//                CreateProductCategoryRequestDto.of(productUuid,
//                        createProductRequestDto.getMainCategoryId(), createProductRequestDto.getSubCategoryId()));

        // 트랜잭션 커밋 후 Kafka 메시지 발행
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                ProductEvent event = productEventFactory.createEvent(productUuid);
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
