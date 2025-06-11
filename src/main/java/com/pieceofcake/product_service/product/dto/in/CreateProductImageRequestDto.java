package com.pieceofcake.product_service.product.dto.in;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateProductImageRequestDto {
    private String productUuid;
    private List<ProductImageRequestDto> productImageRequestDtoList;

    @Builder
    public CreateProductImageRequestDto(String productUuid, List<ProductImageRequestDto> productImageRequestDtoList) {
        this.productUuid = productUuid;
        this.productImageRequestDtoList = productImageRequestDtoList;
    }

    public static CreateProductImageRequestDto of(String productUuid, List<ProductImageRequestDto> productImageRequestDtoList) {
        return CreateProductImageRequestDto.builder()
                .productUuid(productUuid)
                .productImageRequestDtoList(productImageRequestDtoList)
                .build();
    }
}
