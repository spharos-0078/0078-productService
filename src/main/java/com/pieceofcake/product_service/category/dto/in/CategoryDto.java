package com.pieceofcake.product_service.category.dto.in;

import com.pieceofcake.product_service.category.vo.in.CategoryVo;
import com.pieceofcake.product_service.kafka.producer.event.CategoryEvent;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryDto {
    private Integer categoryId;
    private String categoryName;

    @Builder
    public CategoryDto(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public static CategoryDto from(CategoryVo vo){
        return CategoryDto.builder()
                .categoryId(vo.getCategoryId())
                .categoryName(vo.getCategoryName())
                .build();
    }

    public CategoryEvent toEvent(){
        return CategoryEvent.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .build();
    }
}