package com.pieceofcake.product_service.ai.dto.in;

import com.pieceofcake.product_service.ai.vo.in.AiRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AiRequestDto {
    private String description;  // 물품 설명
    private String imageUrl;

    @Builder
    public AiRequestDto(String description, String imageUrl) {
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public static AiRequestDto from(AiRequestVo aiRequestVo) {
        return AiRequestDto.builder()
                .description(aiRequestVo.getDescription())
                .imageUrl(aiRequestVo.getImageUrl())
                .build();
    }

    public static AiRequestDto of(String description, String fileName) {
        return AiRequestDto.builder()
                .description(description)
                .imageUrl(fileName)
                .build();
    }
}
