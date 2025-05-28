package com.pieceofcake.product_service.presentation;

import com.pieceofcake.product_service.application.ProductServiceImpl;
import com.pieceofcake.product_service.common.entity.BaseResponseEntity;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.dto.in.ProductCreateRequestDto;
import com.pieceofcake.product_service.dto.in.ProductUpdateRequestDto;
import com.pieceofcake.product_service.dto.out.ProductGetUuidResponseDto;
import com.pieceofcake.product_service.vo.in.ProductCreateRequestVo;
import com.pieceofcake.product_service.vo.in.ProductUpdateRequestVo;
import com.pieceofcake.product_service.vo.out.ProductGetUuidResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    public BaseResponseEntity<Void> updateProduct(@RequestBody ProductUpdateRequestVo productUpdateRequestVo) {
        productService.updateProduct(ProductUpdateRequestDto.from(productUpdateRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @GetMapping("/list")
    public BaseResponseEntity<List<ProductGetUuidResponseVo>> getProductUuidList(){
        return new BaseResponseEntity<>(productService.getProductUuidList().stream()
                .map(ProductGetUuidResponseDto::toVo).toList());
    }
}
