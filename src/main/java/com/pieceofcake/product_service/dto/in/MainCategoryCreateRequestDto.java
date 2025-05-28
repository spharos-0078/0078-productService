package com.pieceofcake.product_service.dto.in;


import com.pieceofcake.product_service.entity.MainCategory;
import com.pieceofcake.product_service.vo.in.MainCategoryCreateRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MainCategoryCreateRequestDto {
    private String name;

    @Builder
    public MainCategoryCreateRequestDto(String name) {
        this.name = name;
    }

    public static MainCategoryCreateRequestDto from(MainCategoryCreateRequestVo mainCategoryCreateRequestVo) {
        return MainCategoryCreateRequestDto.builder()
                .name(mainCategoryCreateRequestVo.getName())
                .build();
    }

    public MainCategory toEntity() {
        return MainCategory.builder()
                .mainCategoryName(name)
                .build();
    }
}
