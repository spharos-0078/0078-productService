package com.pieceofcake.product_service.product.dto.out;

import com.pieceofcake.product_service.product.entity.Product;
import com.pieceofcake.product_service.product.vo.out.GetProductUuidResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetProductUuidResponseDto {
    private String productUuid;

    @Builder
    public GetProductUuidResponseDto(String productUuid) {
        this.productUuid = productUuid;
    }

    public static GetProductUuidResponseDto from(Product product){
        return GetProductUuidResponseDto.builder()
                .productUuid(product.getProductUuid())
                .build();
    }

    public GetProductUuidResponseVo toVo(){
        return GetProductUuidResponseVo.builder()
                .productUuid(productUuid)
                .build();
    }

}
