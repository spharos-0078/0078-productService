package com.pieceofcake.product_service.category.dto.in;

import com.pieceofcake.product_service.kafka.consumer.event.ProductReadEvent;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateCategoryNameEventDto {
    private String productUuid;
    private Integer mainCategoryId;
    private Integer subCategoryId;

    @Builder
    public CreateCategoryNameEventDto(String productUuid, Integer mainCategoryId, Integer subCategoryId) {
        this.productUuid = productUuid;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
    }

    public static CreateCategoryNameEventDto from(ProductReadEvent event){
        return CreateCategoryNameEventDto.builder()
                .productUuid(event.getProductUuid())
                .mainCategoryId(event.getMainCategoryId())
                .subCategoryId(event.getSubCategoryId())
                .build();
    }
}
