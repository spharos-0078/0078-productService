package com.pieceofcake.product_service.kafka.event;

import com.pieceofcake.product_service.product.entity.ProductImage;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductImageEvent {
    private String imageUrl;
    private Integer imageIndex;
    private Boolean isThumbnail;

    public static ProductImageEvent from(ProductImage productImage){
        return ProductImageEvent.builder()
                .imageUrl(productImage.getImageUrl())
                .imageIndex(productImage.getImageIndex())
                .isThumbnail(productImage.getIsThumbnail())
                .build();
    }
}
