package com.pieceofcake.product_service.product.dto.in;

import com.pieceofcake.product_service.category.dto.in.CategoryDto;
import com.pieceofcake.product_service.product.entity.Product;
import com.pieceofcake.product_service.product.entity.ProductStatus;
import com.pieceofcake.product_service.product.vo.in.UpdateProductRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class UpdateProductRequestDto {
    private String productUuid;
    private String productName;
    private Long aiEstimatedPrice;
    private Long purchasePrice;
    private ProductStatus productStatus;
    private String storageLocation;
    private String description;
    private List<ProductImageRequestDto> productImageRequestDtoList;
    private CategoryDto mainCategory;
    private CategoryDto subCategory;

    @Builder
    public UpdateProductRequestDto(String productUuid, String productName, Long aiEstimatedPrice, Long purchasePrice,
                                   ProductStatus productStatus, String storageLocation, String description, List<ProductImageRequestDto> productImageRequestDtoList, CategoryDto mainCategory, CategoryDto subCategory) {
        this.productUuid = productUuid;
        this.productName = productName;
        this.aiEstimatedPrice = aiEstimatedPrice;
        this.purchasePrice = purchasePrice;
        this.productStatus = productStatus;
        this.storageLocation = storageLocation;
        this.description = description;
        this.productImageRequestDtoList = productImageRequestDtoList;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

    public static UpdateProductRequestDto from(UpdateProductRequestVo vo) {
        return UpdateProductRequestDto.builder()
                .productUuid(vo.getProductUuid())
                .productName(vo.getProductName())
                .aiEstimatedPrice(vo.getAiEstimatedPrice())
                .purchasePrice(vo.getPurchasePrice())
                .productStatus(vo.getProductStatus())
                .storageLocation(vo.getStorageLocation())
                .description(vo.getDescription())
                .productImageRequestDtoList( vo.getProductImageRequestVoList() == null ? null :
                        vo.getProductImageRequestVoList().stream()
                                .map(ProductImageRequestDto::from)
                                .toList())
                .mainCategory(CategoryDto.from(vo.getMainCategory()))
                .subCategory(CategoryDto.from(vo.getSubCategory()))
                .build();
    }

    public Product toEntity(Product product){
        return Product.builder()
                .id(product.getId())
                .productUuid(product.getProductUuid())
                .productName(productName == null ? product.getProductName() : productName)
                .aiEstimatedPrice(aiEstimatedPrice == null ? product.getAiEstimatedPrice() : aiEstimatedPrice)
                .purchasePrice(purchasePrice == null ? product.getPurchasePrice() : purchasePrice)
                .productStatus(productStatus == null ? product.getProductStatus() : productStatus)
                .storageLocation(storageLocation == null ? product.getStorageLocation() : storageLocation)
                .description(description == null ? product.getDescription() : description)
                .build();
    }
}
