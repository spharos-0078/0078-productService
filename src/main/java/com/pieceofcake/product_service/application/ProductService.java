package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.dto.in.ProductCreateRequestDto;
import com.pieceofcake.product_service.dto.in.ProductUpdateRequestDto;
import com.pieceofcake.product_service.dto.out.ProductGetUuidResponseDto;

import java.util.List;

public interface ProductService {
    void createProduct(ProductCreateRequestDto productCreateRequestDto);
    void updateProduct(ProductUpdateRequestDto productUpdateRequestDto);
    List<ProductGetUuidResponseDto> getProductUuidList();
}