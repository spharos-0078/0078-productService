package com.pieceofcake.product_service.product.entity;

import com.pieceofcake.product_service.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "product_image")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductImage extends BaseEntity {  // productMedia(영상 추가시), NoSQL
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_uuid", nullable = false, length = 50)
    private String productUuid;

    @Column(name = "image_url", nullable = false, length = 255)
    private String imageUrl;

    @Column(name = "image_index", nullable = false)
    private Integer imageIndex;

    @Column(name = "is_thumbnail", nullable = false)
    private Boolean isThumbnail;

    @Builder
    public ProductImage(Long id, String productUuid, String imageUrl, Integer imageIndex, Boolean isThumbnail) {
        this.id = id;
        this.productUuid = productUuid;
        this.imageUrl = imageUrl;
        this.imageIndex = imageIndex;
        this.isThumbnail = isThumbnail;
    }
}
