package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.dto.in.ProductCreateRequestDto;
import com.pieceofcake.product_service.dto.in.ProductUpdateRequestDto;

public interface ProductService {
    void createProduct(ProductCreateRequestDto productCreateRequestDto);
    void updateProduct(ProductUpdateRequestDto productUpdateRequestDto);
}