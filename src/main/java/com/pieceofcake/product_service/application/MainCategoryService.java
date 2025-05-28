package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.dto.in.MainCategoryCreateRequestDto;

public interface MainCategoryService {
    void createMainCategory(MainCategoryCreateRequestDto mainCategoryCreateRequestDto);
}
