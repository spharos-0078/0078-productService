package com.pieceofcake.product_service.presentation;

import com.pieceofcake.product_service.application.SubCategoryService;
import com.pieceofcake.product_service.common.entity.BaseResponseEntity;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.dto.in.SubCategoryCreateRequestDto;
import com.pieceofcake.product_service.vo.in.SubCategoryCreateRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/sub-category")
@RequiredArgsConstructor
@RestController
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    @PostMapping
    public BaseResponseEntity<Void> createSubCategory(
            @RequestBody SubCategoryCreateRequestVo subCategoryCreateRequestVo) {
        subCategoryService.createSubCategory(SubCategoryCreateRequestDto.from(subCategoryCreateRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
