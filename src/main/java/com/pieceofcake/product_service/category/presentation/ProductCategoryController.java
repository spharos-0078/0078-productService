package com.pieceofcake.product_service.category.presentation;

import com.pieceofcake.product_service.category.application.ProductCategoryServiceImpl;
import com.pieceofcake.product_service.common.entity.BaseResponseEntity;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.category.dto.in.CreateProductCategoryRequestDto;
import com.pieceofcake.product_service.category.vo.in.CreateProductCategoryRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/product-category")
@RequiredArgsConstructor
@RestController
public class ProductCategoryController {
    private final ProductCategoryServiceImpl productCategoryService;

    @PostMapping
    public BaseResponseEntity<Void> createProductCategoryList(
            @RequestBody CreateProductCategoryRequestVo productCategoryListCreateRequestVo) {
        productCategoryService.createProductCategory(
                CreateProductCategoryRequestDto.from(productCategoryListCreateRequestVo));

        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
