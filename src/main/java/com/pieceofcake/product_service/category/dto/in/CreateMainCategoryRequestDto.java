package com.pieceofcake.product_service.category.dto.in;


import com.pieceofcake.product_service.category.entity.MainCategory;
import com.pieceofcake.product_service.category.vo.in.CreateMainCategoryRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateMainCategoryRequestDto {
    private String name;

    @Builder
    public CreateMainCategoryRequestDto(String name) {
        this.name = name;
    }

    public static CreateMainCategoryRequestDto from(CreateMainCategoryRequestVo createMainCategoryRequestVo) {
        return CreateMainCategoryRequestDto.builder()
                .name(createMainCategoryRequestVo.getName())
                .build();
    }

    public MainCategory toEntity() {
        return MainCategory.builder()
                .mainCategoryName(name)
                .build();
    }
}
