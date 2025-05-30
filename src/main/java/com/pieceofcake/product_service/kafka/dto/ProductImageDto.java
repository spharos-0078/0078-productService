package com.pieceofcake.product_service.kafka.dto;

import com.pieceofcake.product_service.product.entity.ProductImage;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductImageDto {
    private String imageUrl;
    private Integer imageIndex;
    private Boolean isThumbnail;

    public static ProductImageDto from(ProductImage productImage){
        return ProductImageDto.builder()
                .imageUrl(productImage.getImageUrl())
                .imageIndex(productImage.getImageIndex())
                .isThumbnail(productImage.getIsThumbnail())
                .build();
    }
}
