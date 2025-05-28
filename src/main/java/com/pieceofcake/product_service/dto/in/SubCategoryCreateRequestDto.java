package com.pieceofcake.product_service.dto.in;


import com.pieceofcake.product_service.entity.SubCategory;
import com.pieceofcake.product_service.vo.in.SubCategoryCreateRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SubCategoryCreateRequestDto {
    private String name;
    private Integer mainCategoryId;

    @Builder
    public SubCategoryCreateRequestDto(String name, Integer mainCategoryId) {
        this.name = name;
        this.mainCategoryId = mainCategoryId;
    }

    public static SubCategoryCreateRequestDto from(SubCategoryCreateRequestVo subCategoryCreateRequestVo) {
        return SubCategoryCreateRequestDto.builder()
                .name(subCategoryCreateRequestVo.getName())
                .mainCategoryId(subCategoryCreateRequestVo.getMainCategoryId())
                .build();
    }

    public SubCategory toEntity() {
        return SubCategory.builder()
                .subCategoryName(name)
                .mainCategoryId(mainCategoryId)
                .build();
    }
}
