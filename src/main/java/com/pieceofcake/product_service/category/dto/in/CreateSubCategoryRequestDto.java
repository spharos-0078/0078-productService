package com.pieceofcake.product_service.category.dto.in;


import com.pieceofcake.product_service.category.entity.SubCategory;
import com.pieceofcake.product_service.category.vo.in.CreateSubCategoryRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateSubCategoryRequestDto {
    private String name;
    private Integer mainCategoryId;

    @Builder
    public CreateSubCategoryRequestDto(String name, Integer mainCategoryId) {
        this.name = name;
        this.mainCategoryId = mainCategoryId;
    }

    public static CreateSubCategoryRequestDto from(CreateSubCategoryRequestVo createSubCategoryRequestVo) {
        return CreateSubCategoryRequestDto.builder()
                .name(createSubCategoryRequestVo.getName())
                .mainCategoryId(createSubCategoryRequestVo.getMainCategoryId())
                .build();
    }

    public SubCategory toEntity() {
        return SubCategory.builder()
                .subCategoryName(name)
                .mainCategoryId(mainCategoryId)
                .build();
    }
}
