package com.pieceofcake.product_service.product.application;

import com.pieceofcake.product_service.product.dto.in.CreateProductImageRequestDto;

public interface ProductImageService {
    void createProductImage(CreateProductImageRequestDto createProductImageRequestDtoList);
}