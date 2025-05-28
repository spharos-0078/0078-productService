package com.pieceofcake.product_service.dto.out;

import com.pieceofcake.product_service.entity.Product;
import com.pieceofcake.product_service.vo.out.ProductGetUuidResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductGetUuidResponseDto {
    private String productUuid;

    @Builder
    public ProductGetUuidResponseDto(String productUuid) {
        this.productUuid = productUuid;
    }

    public static ProductGetUuidResponseDto from(Product product){
        return ProductGetUuidResponseDto.builder()
                .productUuid(product.getProductUuid())
                .build();
    }

    public ProductGetUuidResponseVo toVo(){
        return ProductGetUuidResponseVo.builder()
                .productUuid(productUuid)
                .build();
    }

}
