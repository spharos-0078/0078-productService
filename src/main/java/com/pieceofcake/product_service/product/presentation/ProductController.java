package com.pieceofcake.product_service.product.presentation;

import com.pieceofcake.product_service.common.entity.BaseResponseEntity;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.product.application.ProductServiceImpl;
import com.pieceofcake.product_service.product.dto.in.CreateProductRequestDto;
import com.pieceofcake.product_service.product.dto.in.UpdateProductRequestDto;
import com.pieceofcake.product_service.product.dto.out.GetProductUuidResponseDto;
import com.pieceofcake.product_service.product.vo.in.CreateProductRequestVo;
import com.pieceofcake.product_service.product.vo.in.UpdateProductRequestVo;
import com.pieceofcake.product_service.product.vo.out.GetProductUuidResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/product")
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping
    public BaseResponseEntity<Void> createProduct(@RequestBody CreateProductRequestVo createProductRequestVo) {
        productService.createProduct(CreateProductRequestDto.from(createProductRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @PutMapping
    public BaseResponseEntity<Void> updateProduct(@RequestBody UpdateProductRequestVo updateProductRequestVo) {
        productService.updateProduct(UpdateProductRequestDto.from(updateProductRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @GetMapping("/list")
    public BaseResponseEntity<List<GetProductUuidResponseVo>> getProductUuidList() {
        return new BaseResponseEntity<>(productService.getProductUuidList().stream()
                .map(GetProductUuidResponseDto::toVo).toList());
    }

    @DeleteMapping("/{productUuid}")
    public BaseResponseEntity<Void> deleteProduct(@PathVariable String productUuid) {
        productService.deleteProduct(productUuid);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
