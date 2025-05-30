package com.pieceofcake.product_service.category.application;

import com.pieceofcake.product_service.category.dto.in.CreateProductCategoryRequestDto;

public interface ProductCategoryService {
    void createProductCategory(CreateProductCategoryRequestDto createProductCategoryRequestDto);
}
