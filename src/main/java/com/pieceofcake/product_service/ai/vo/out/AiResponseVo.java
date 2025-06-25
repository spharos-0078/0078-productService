package com.pieceofcake.product_service.ai.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AiResponseVo {
    private String model;
    private String result;

    @Builder
    public AiResponseVo(String model, String result) {
        this.model = model;
        this.result = result;
    }
}