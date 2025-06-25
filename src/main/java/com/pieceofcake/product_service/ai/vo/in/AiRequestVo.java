package com.pieceofcake.product_service.ai.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AiRequestVo {
    private String description;
    private String imageUrl;
}