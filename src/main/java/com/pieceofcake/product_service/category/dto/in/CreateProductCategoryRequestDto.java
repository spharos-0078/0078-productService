package com.pieceofcake.product_service.category.dto.in;

import com.pieceofcake.product_service.category.entity.ProductCategory;
import com.pieceofcake.product_service.category.vo.in.CreateProductCategoryRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateProductCategoryRequestDto {
    private Integer mainCategoryId;
    private Integer subCategoryId;
    private String productUuid;

    @Builder
    public CreateProductCategoryRequestDto(Integer mainCategoryId, Integer subCategoryId, String productUuid) {
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.productUuid = productUuid;
    }

    public static CreateProductCategoryRequestDto from(CreateProductCategoryRequestVo vo) {
        return CreateProductCategoryRequestDto.builder()
                .productUuid(vo.getProductUuid())
                .mainCategoryId(vo.getMainCategoryId())
                .subCategoryId(vo.getSubCategoryId())
                .build();
    }

    public static CreateProductCategoryRequestDto of(String productUuid, Integer mainCategoryId, Integer subCategoryId){
        return CreateProductCategoryRequestDto.builder()
                .productUuid(productUuid)
                .mainCategoryId(mainCategoryId)
                .subCategoryId(subCategoryId)
                .build();
    }

    public ProductCategory toEntity() {
        return ProductCategory.builder()
                .productUuid(productUuid)
                .mainCategoryId(mainCategoryId)
                .subCategoryId(subCategoryId)
                .build();
    }
}
