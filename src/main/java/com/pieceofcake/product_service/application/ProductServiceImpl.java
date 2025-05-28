package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.common.entity.BaseEntity;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.common.exception.BaseException;
import com.pieceofcake.product_service.dto.in.ProductCreateRequestDto;
import com.pieceofcake.product_service.dto.in.ProductImageCreateRequestDto;
import com.pieceofcake.product_service.dto.in.ProductUpdateRequestDto;
import com.pieceofcake.product_service.entity.Product;
import com.pieceofcake.product_service.infrastructure.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImageServiceImpl productImageService;

    @Transactional
    @Override
    public void createProduct(ProductCreateRequestDto productCreateRequestDto) {
        String productUuid = UUID.randomUUID().toString();
        productRepository.save(productCreateRequestDto.toEntity(productUuid));

        productImageService.createProductImage(ProductImageCreateRequestDto.of(
                productUuid, productCreateRequestDto.getProductImageRequestDtoList()));
    }

    @Override
    public void updateProduct(ProductUpdateRequestDto productUpdateRequestDto) {
        Product product = productRepository.findByProductUuid(productUpdateRequestDto.getProductUuid())
                .orElseThrow( () -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));

        productRepository.save(productUpdateRequestDto.toEntity(product));
    }
}
