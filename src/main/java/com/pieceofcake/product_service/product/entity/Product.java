package com.pieceofcake.product_service.product.entity;

import com.pieceofcake.product_service.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_uuid", nullable = false, length = 50)
    private String productUuid;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "ai_estimated_price", nullable = false)
    private Long aiEstimatedPrice;

    @Lob
    @Column(name = "ai_estimated_description", columnDefinition = "LONGTEXT")
    private String aiEstimatedDescription;

    @Column(name = "purchase_price", nullable = false)
    private Long purchasePrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status", nullable = false, length = 20)
    private ProductStatus productStatus;

    @Column(name = "storage_location", nullable = false, length = 100)
    private String storageLocation;

    @Lob
    @Column(name = "description", nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}
