package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.dto.in.ProductImageCreateRequestDto;

public interface ProductImageService {
    void createProductImage(ProductImageCreateRequestDto productImageCreateRequestDtoList);
}