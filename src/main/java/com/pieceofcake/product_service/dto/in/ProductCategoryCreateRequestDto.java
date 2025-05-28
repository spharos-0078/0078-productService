package com.pieceofcake.product_service.dto.in;

import com.pieceofcake.product_service.entity.ProductCategory;
import com.pieceofcake.product_service.vo.in.ProductCategoryCreateRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductCategoryCreateRequestDto {
    private Integer mainCategoryId;
    private Integer subCategoryId;
    private String productUuid;

    @Builder
    public ProductCategoryCreateRequestDto(Integer mainCategoryId, Integer subCategoryId, String productUuid) {
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.productUuid = productUuid;
    }

    public static ProductCategoryCreateRequestDto from(ProductCategoryCreateRequestVo vo) {
        return ProductCategoryCreateRequestDto.builder()
                .productUuid(vo.getProductUuid())
                .mainCategoryId(vo.getMainCategoryId())
                .subCategoryId(vo.getSubCategoryId())
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
