package com.pieceofcake.product_service.product.dto.in;

import com.pieceofcake.product_service.product.entity.ProductImage;
import com.pieceofcake.product_service.product.vo.in.ProductImageRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductImageRequestDto {
    private Integer imageIndex;
    private Boolean isThumbnail;
    private String fileName;

    @Builder
    public ProductImageRequestDto(Integer imageIndex, Boolean isThumbnail, String fileName) {
        this.imageIndex = imageIndex;
        this.isThumbnail = isThumbnail;
        this.fileName = fileName;
    }

    public static ProductImageRequestDto from(ProductImageRequestVo vo){
        return ProductImageRequestDto.builder()
                .imageIndex(vo.getImageIndex())
                .isThumbnail(vo.getIsThumbnail())
                .fileName(vo.getFileName())
                .build();
    }

    public ProductImage toEntity(String productUuid){
        return ProductImage.builder()
                .productUuid(productUuid)
                .imageIndex(this.imageIndex)
                .isThumbnail(this.isThumbnail)
                .imageUrl(this.fileName)
                .build();
    }
}
