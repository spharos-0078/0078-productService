package com.pieceofcake.product_service.presentation;

import com.pieceofcake.product_service.application.MainCategoryServiceImpl;
import com.pieceofcake.product_service.common.entity.BaseResponseEntity;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.dto.in.MainCategoryCreateRequestDto;
import com.pieceofcake.product_service.vo.in.MainCategoryCreateRequestVo;
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
    public BaseResponseEntity<Void> createMainCategory(@RequestBody MainCategoryCreateRequestVo mainCategoryCreateRequestVo) {
        mainCategoryService.createMainCategory(MainCategoryCreateRequestDto.from(mainCategoryCreateRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}

