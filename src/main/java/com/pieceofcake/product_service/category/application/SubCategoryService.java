package com.pieceofcake.product_service.category.application;

import com.pieceofcake.product_service.category.dto.in.CreateSubCategoryRequestDto;

public interface SubCategoryService {
    void createSubCategory(CreateSubCategoryRequestDto createSubCategoryRequestDto);
}
