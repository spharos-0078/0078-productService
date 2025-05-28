package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.dto.in.ProductImageCreateRequestDto;
import com.pieceofcake.product_service.infrastructure.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;

    @Override
    public void createProductImage(ProductImageCreateRequestDto productImageCreateRequestDtoList) {
        productImageRepository.saveAll(productImageCreateRequestDtoList.getProductImageRequestDtoList()
                .stream()
                .map(dto -> dto.toEntity(productImageCreateRequestDtoList.getProductUuid()))
                .toList());
    }
}
