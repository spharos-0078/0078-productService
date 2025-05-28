package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.dto.in.ProductCategoryCreateRequestDto;

public interface ProductCategoryService {
    void createProductCategory(ProductCategoryCreateRequestDto productCategoryCreateRequestDto);
}
