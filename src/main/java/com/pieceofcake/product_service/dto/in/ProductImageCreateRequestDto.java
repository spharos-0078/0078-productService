package com.pieceofcake.product_service.dto.in;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductImageCreateRequestDto {
    private String productUuid;
    private List<ProductImageRequestDto> productImageRequestDtoList;

    @Builder
    public ProductImageCreateRequestDto(String productUuid, List<ProductImageRequestDto> productImageRequestDtoList) {
        this.productUuid = productUuid;
        this.productImageRequestDtoList = productImageRequestDtoList;
    }

    public static ProductImageCreateRequestDto of(String productUuid, List<ProductImageRequestDto> productImageRequestDtoList) {
        return ProductImageCreateRequestDto.builder()
                .productUuid(productUuid)
                .productImageRequestDtoList(productImageRequestDtoList)
                .build();
    }
}
