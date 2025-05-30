package com.pieceofcake.product_service.category.presentation;

import com.pieceofcake.product_service.category.application.SubCategoryServiceImpl;
import com.pieceofcake.product_service.common.entity.BaseResponseEntity;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.category.dto.in.CreateSubCategoryRequestDto;
import com.pieceofcake.product_service.category.vo.in.CreateSubCategoryRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/sub-category")
@RequiredArgsConstructor
@RestController
public class SubCategoryController {
    private final SubCategoryServiceImpl subCategoryService;

    @PostMapping
    public BaseResponseEntity<Void> createSubCategory(
            @RequestBody CreateSubCategoryRequestVo createSubCategoryRequestVo) {
        subCategoryService.createSubCategory(CreateSubCategoryRequestDto.from(createSubCategoryRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
