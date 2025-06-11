package com.pieceofcake.product_service.category.dto.in;

import com.pieceofcake.product_service.category.entity.SubCategory;
import com.pieceofcake.product_service.category.vo.in.UpdateSubCategoryRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateSubCategoryRequestDto {
    private Integer id;
    private String categoryName;
    private Integer mainCategoryId;

    @Builder
    public UpdateSubCategoryRequestDto(Integer id, String categoryName, Integer mainCategoryId) {
        this.id = id;
        this.categoryName = categoryName;
        this.mainCategoryId = mainCategoryId;
    }

    public static UpdateSubCategoryRequestDto from(UpdateSubCategoryRequestVo vo) {
        return UpdateSubCategoryRequestDto.builder()
                .id(vo.getId())
                .categoryName(vo.getCategoryName())
                .mainCategoryId(vo.getMainCategoryId())
                .build();
    }

    public SubCategory toEntity(SubCategory subCategory) {
        return SubCategory.builder()
                .id(subCategory.getId())
                .subCategoryName(categoryName == null ? subCategory.getSubCategoryName() : categoryName)
                .mainCategoryId(mainCategoryId == null ? subCategory.getMainCategoryId() : mainCategoryId)
                .build();
    }
}
