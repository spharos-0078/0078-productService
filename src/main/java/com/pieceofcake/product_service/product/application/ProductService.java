package com.pieceofcake.product_service.product.application;

import com.pieceofcake.product_service.product.dto.in.CreateProductRequestDto;
import com.pieceofcake.product_service.product.dto.in.UpdateProductInfoRequestDto;
import com.pieceofcake.product_service.product.dto.in.UpdateProductRequestDto;
import com.pieceofcake.product_service.product.dto.out.GetProductUuidResponseDto;

import java.util.List;

public interface ProductService {
    void createProduct(CreateProductRequestDto createProductRequestDto);
//    void updateProduct(UpdateProductInfoRequestDto updateProductInfoRequestDto);
    void updateProduct(UpdateProductRequestDto updateProductRequestDto);
    List<GetProductUuidResponseDto> getProductUuidList();
    void deleteProduct(String productUuid);
}