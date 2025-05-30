package com.pieceofcake.product_service.presentation;

import com.pieceofcake.product_service.application.ProductServiceImpl;
import com.pieceofcake.product_service.common.entity.BaseResponseEntity;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.dto.in.ProductCreateRequestDto;
import com.pieceofcake.product_service.vo.in.ProductCreateRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/product")
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping
    public BaseResponseEntity<Void> createProduct(@RequestBody ProductCreateRequestVo productCreateRequestVo) {
        productService.createProduct(ProductCreateRequestDto.from(productCreateRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
