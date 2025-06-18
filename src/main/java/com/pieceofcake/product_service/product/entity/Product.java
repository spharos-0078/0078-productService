package com.pieceofcake.product_service.product.entity;

import com.pieceofcake.product_service.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "ai_estimated_description")
    private String aiEstimatedDescription;

    @Column(name = "purchase_price", nullable = false)
    private Long purchasePrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status", nullable = false, length = 20)
    private ProductStatus productStatus;

    @Column(name = "storage_location", nullable = false, length = 100)
    private String storageLocation;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Builder
    public Product(Long id, String productUuid, String productName, Long aiEstimatedPrice, String aiEstimatedDescription,
                   Long purchasePrice, ProductStatus productStatus, String storageLocation, String description, Boolean isDeleted) {
        this.id = id;
        this.productUuid = productUuid;
        this.productName = productName;
        this.aiEstimatedPrice = aiEstimatedPrice;
        this.aiEstimatedDescription = aiEstimatedDescription;
        this.purchasePrice = purchasePrice;
        this.productStatus = productStatus;
        this.storageLocation = storageLocation;
        this.description = description;
        this.isDeleted = isDeleted;
    }
}
