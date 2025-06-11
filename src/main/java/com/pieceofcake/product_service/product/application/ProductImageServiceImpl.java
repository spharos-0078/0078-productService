package com.pieceofcake.product_service.product.application;

import com.pieceofcake.product_service.product.dto.in.CreateProductImageRequestDto;
import com.pieceofcake.product_service.product.infrastructure.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;

    @Override
    public void createProductImage(CreateProductImageRequestDto createProductImageRequestDtoList) {
        productImageRepository.saveAll(createProductImageRequestDtoList.getProductImageRequestDtoList()
                .stream()
                .map(dto -> dto.toEntity(createProductImageRequestDtoList.getProductUuid()))
                .toList());
    }
}
