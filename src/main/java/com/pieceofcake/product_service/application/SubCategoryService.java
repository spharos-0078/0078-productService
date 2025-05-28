package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.dto.in.SubCategoryCreateRequestDto;

public interface SubCategoryService {
    void createSubCategory(SubCategoryCreateRequestDto subCategoryCreateRequestDto);
}
