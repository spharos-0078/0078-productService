package com.pieceofcake.product_service.ai.dto.out;

import com.pieceofcake.product_service.ai.vo.out.AiResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AiResponseDto {
    private String model;
    private String result;

    @Builder
    public AiResponseDto(String model, String result) {
        this.model = model;
        this.result = result;
    }

    public static AiResponseDto of(String model, String result) {
        return AiResponseDto.builder()
                .model(model)
                .result(result)
                .build();
    }

    public AiResponseVo toVo() {
        return AiResponseVo.builder()
                .model(model)
                .result(result)
                .build();
    }
}
