package com.pieceofcake.product_service.category.dto.out;

import com.pieceofcake.product_service.category.entity.MainCategory;
import com.pieceofcake.product_service.category.vo.out.GetMainCategoryResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetMainCategoryResponseDto {
    private Integer id;
    private String categoryName;

    @Builder
    public GetMainCategoryResponseDto(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public static GetMainCategoryResponseDto from(MainCategory mainCategory) {
        return GetMainCategoryResponseDto.builder()
                .id(mainCategory.getId())
                .categoryName(mainCategory.getMainCategoryName())
                .build();
    }

    public GetMainCategoryResponseVo toVo() {
        return GetMainCategoryResponseVo.builder()
                .id(id)
                .categoryName(categoryName)
                .build();
    }
}
