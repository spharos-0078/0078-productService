package com.pieceofcake.product_service.category.application;

import com.pieceofcake.product_service.category.dto.in.CreateMainCategoryRequestDto;

public interface MainCategoryService {
    void createMainCategory(CreateMainCategoryRequestDto createMainCategoryRequestDto);
}
