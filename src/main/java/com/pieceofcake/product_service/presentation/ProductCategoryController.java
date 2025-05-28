package com.pieceofcake.product_service.presentation;

import com.pieceofcake.product_service.application.ProductCategoryServiceImpl;
import com.pieceofcake.product_service.common.entity.BaseResponseEntity;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.dto.in.ProductCategoryCreateRequestDto;
import com.pieceofcake.product_service.vo.in.ProductCategoryCreateRequestVo;
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
            @RequestBody ProductCategoryCreateRequestVo productCategoryListCreateRequestVo) {
        productCategoryService.createProductCategory(
                ProductCategoryCreateRequestDto.from(productCategoryListCreateRequestVo));

        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
