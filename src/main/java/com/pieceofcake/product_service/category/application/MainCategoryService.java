package com.pieceofcake.product_service.category.application;

import com.pieceofcake.product_service.category.dto.in.CreateMainCategoryRequestDto;
import com.pieceofcake.product_service.category.dto.in.UpdateMainCategoryRequestDto;
import com.pieceofcake.product_service.category.dto.out.GetMainCategoryResponseDto;

import java.util.List;

public interface MainCategoryService {
    void createMainCategory(CreateMainCategoryRequestDto createMainCategoryRequestDto);

    List<GetMainCategoryResponseDto> getMainCategoryList();

    GetMainCategoryResponseDto getMainCategory(Integer mainCategoryId);

    void updateMainCategory(UpdateMainCategoryRequestDto updateMainCategoryRequestDto);

    void deleteMainCategory(Integer mainCategoryId);
}
