package com.pieceofcake.product_service.category.dto.out;

import com.pieceofcake.product_service.category.entity.SubCategory;
import com.pieceofcake.product_service.category.vo.out.GetSubCategoryResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetSubCategoryResponseDto {
    private Integer id;
    private String categoryName;

    @Builder
    public GetSubCategoryResponseDto(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public static GetSubCategoryResponseDto from(SubCategory subCategory) {
        return GetSubCategoryResponseDto.builder()
                .id(subCategory.getId())
                .categoryName(subCategory.getSubCategoryName())
                .build();
    }

    public GetSubCategoryResponseVo toVo() {
        return GetSubCategoryResponseVo.builder()
                .id(id)
                .categoryName(categoryName)
                .build();
    }
}
