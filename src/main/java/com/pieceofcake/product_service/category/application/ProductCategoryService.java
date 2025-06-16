package com.pieceofcake.product_service.category.application;

import com.pieceofcake.product_service.category.dto.in.CreateCategoryNameEventDto;

public interface ProductCategoryService {
    void createCategoryNameRead(CreateCategoryNameEventDto createCategoryNameEventDto);
}
