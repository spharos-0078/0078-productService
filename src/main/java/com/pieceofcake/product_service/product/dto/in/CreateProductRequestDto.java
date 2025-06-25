package com.pieceofcake.product_service.product.dto.in;

import com.pieceofcake.product_service.category.dto.in.CategoryDto;
import com.pieceofcake.product_service.product.entity.Product;
import com.pieceofcake.product_service.product.entity.ProductStatus;
import com.pieceofcake.product_service.product.vo.in.CreateProductRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateProductRequestDto {
    private String productName;
    private Long purchasePrice;
    private ProductStatus status;
    private String storageLocation;
    private String description;
    private List<ProductImageRequestDto> productImageRequestDtoList;
    private CategoryDto mainCategory;
    private CategoryDto subCategory;

    @Builder
    public CreateProductRequestDto(String productName, Long purchasePrice, ProductStatus status,
                                   String storageLocation, String description, List<ProductImageRequestDto> productImageRequestDtoList,
                                   CategoryDto mainCategory, CategoryDto subCategory) {
        this.productName = productName;
        this.purchasePrice = purchasePrice;
        this.status = status;
        this.storageLocation = storageLocation;
        this.description = description;
        this.productImageRequestDtoList = productImageRequestDtoList;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }


    public static CreateProductRequestDto from(CreateProductRequestVo vo) {
        return CreateProductRequestDto.builder()
                .productName(vo.getProductName())
                .purchasePrice(vo.getPurchasePrice())
                .status(vo.getStatus())
                .storageLocation(vo.getStorageLocation())
                .description(vo.getDescription())
                .productImageRequestDtoList(vo.getProductImageRequestVoList().stream()
                        .map(ProductImageRequestDto::from).toList())
                .mainCategory(CategoryDto.from(vo.getMainCategory()))
                .subCategory(CategoryDto.from(vo.getSubCategory()))
                .build();
    }

    public Product toEntity(String productUuid, Long aiEstimatedPrice, String aiEstimatedDescription) {
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
