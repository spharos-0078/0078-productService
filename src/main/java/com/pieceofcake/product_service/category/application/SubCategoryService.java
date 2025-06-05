package com.pieceofcake.product_service.category.application;

import com.pieceofcake.product_service.category.dto.in.CreateSubCategoryRequestDto;
import com.pieceofcake.product_service.category.dto.in.UpdateSubCategoryRequestDto;
import com.pieceofcake.product_service.category.dto.out.GetSubCategoryResponseDto;

import java.util.List;

public interface SubCategoryService {
    void createSubCategory(CreateSubCategoryRequestDto createSubCategoryRequestDto);

    List<GetSubCategoryResponseDto> getSubCategoryList();

    GetSubCategoryResponseDto getSubCategory(Integer subCategoryId);

    void updateSubCategory(UpdateSubCategoryRequestDto updateSubCategoryRequestDto);

    void deleteSubCategory(Integer subCategoryId);

    List<GetSubCategoryResponseDto> getSubCategoryListByMainCategoryId(Integer mainCategoryId);
}
