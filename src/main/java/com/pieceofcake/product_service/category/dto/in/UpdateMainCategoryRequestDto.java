package com.pieceofcake.product_service.category.dto.in;

import com.pieceofcake.product_service.category.entity.MainCategory;
import com.pieceofcake.product_service.category.vo.in.UpdateMainCategoryRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateMainCategoryRequestDto {
    private Integer id;
    private String categoryName;

    @Builder
    public UpdateMainCategoryRequestDto(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public static UpdateMainCategoryRequestDto from(UpdateMainCategoryRequestVo vo) {
        return UpdateMainCategoryRequestDto.builder()
                .id(vo.getId())
                .categoryName(vo.getCategoryName())
                .build();
    }

    public MainCategory toEntity(MainCategory mainCategory) {
        return MainCategory.builder()
                .id(mainCategory.getId())
                .mainCategoryName(categoryName == null ? mainCategory.getMainCategoryName() : categoryName)
                .build();
    }
}
