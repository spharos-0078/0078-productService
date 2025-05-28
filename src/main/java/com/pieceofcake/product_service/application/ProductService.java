package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.dto.in.ProductCreateRequestDto;

public interface ProductService {
    void createProduct(ProductCreateRequestDto productCreateRequestDto);
}