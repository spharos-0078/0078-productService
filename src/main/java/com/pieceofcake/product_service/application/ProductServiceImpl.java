package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.dto.in.ProductCreateRequestDto;
import com.pieceofcake.product_service.dto.in.ProductImageCreateRequestDto;
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
}
