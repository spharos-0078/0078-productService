package com.pieceofcake.product_service.category.presentation;

import com.pieceofcake.product_service.category.application.MainCategoryServiceImpl;
import com.pieceofcake.product_service.common.entity.BaseResponseEntity;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.category.dto.in.CreateMainCategoryRequestDto;
import com.pieceofcake.product_service.category.vo.in.CreateMainCategoryRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/main-category")
@RequiredArgsConstructor
@RestController
public class MainCategoryController {

    private final MainCategoryServiceImpl mainCategoryService;

    @PostMapping
    public BaseResponseEntity<Void> createMainCategory(@RequestBody CreateMainCategoryRequestVo createMainCategoryRequestVo) {
        mainCategoryService.createMainCategory(CreateMainCategoryRequestDto.from(createMainCategoryRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}

