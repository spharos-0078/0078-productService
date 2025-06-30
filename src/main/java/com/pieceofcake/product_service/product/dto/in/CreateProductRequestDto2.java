package com.pieceofcake.product_service.product.dto.in;

import com.pieceofcake.product_service.category.dto.in.CategoryDto;
import com.pieceofcake.product_service.product.entity.Product;
import com.pieceofcake.product_service.product.entity.ProductStatus;
import com.pieceofcake.product_service.product.vo.in.CreateProductRequestVo;
import com.pieceofcake.product_service.product.vo.in.CreateProductRequestVo2;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateProductRequestDto2 {
    private String productName;
    private Long purchasePrice;
    private ProductStatus status;
    private String storageLocation;
    private String description;
    private List<ProductImageRequestDto> productImageRequestDtoList;
    private CategoryDto mainCategory;
    private CategoryDto subCategory;

    // AI 사용시 주석처리하기
    private Long aiEstimatedPrice;
    private String aiEstimatedDescription;

    // AI 예측 서비스가 없을 경우
    @Builder
    public CreateProductRequestDto2(String productName, Long purchasePrice, ProductStatus status, String storageLocation, String description, List<ProductImageRequestDto> productImageRequestDtoList, CategoryDto mainCategory, CategoryDto subCategory, Long aiEstimatedPrice, String aiEstimatedDescription) {
        this.productName = productName;
        this.purchasePrice = purchasePrice;
        this.status = status;
        this.storageLocation = storageLocation;
        this.description = description;
        this.productImageRequestDtoList = productImageRequestDtoList;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.aiEstimatedPrice = aiEstimatedPrice;
        this.aiEstimatedDescription = aiEstimatedDescription;
    }

    public static CreateProductRequestDto2 from(CreateProductRequestVo2 vo) {
        return CreateProductRequestDto2.builder()
                .productName(vo.getProductName())
                .purchasePrice(vo.getPurchasePrice())
                .status(vo.getStatus())
                .storageLocation(vo.getStorageLocation())
                .description(vo.getDescription())
                .productImageRequestDtoList(vo.getProductImageRequestVoList().stream()
                        .map(ProductImageRequestDto::from).toList())
                .mainCategory(CategoryDto.from(vo.getMainCategory()))
                .subCategory(CategoryDto.from(vo.getSubCategory()))
                .aiEstimatedPrice(vo.getPrice())
                .aiEstimatedDescription(vo.getAiDescription())
                .build();
    }

    public Product toEntity(String productUuid) {
        return Product.builder()
                .productUuid(productUuid)
                .productName(productName)
                .aiEstimatedPrice(aiEstimatedPrice)
                .aiEstimatedDescription(aiEstimatedDescription)
                .purchasePrice(purchasePrice)
                .productStatus(status)
                .storageLocation(storageLocation)
                .description(description)
                .build();
    }
}
