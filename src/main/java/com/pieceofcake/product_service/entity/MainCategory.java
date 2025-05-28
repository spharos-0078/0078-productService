package com.pieceofcake.product_service.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MainCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "main_category_name", nullable = false, length = 50)
    private String mainCategoryName;

    @Builder
    public MainCategory(Integer id, String mainCategoryName) {
        this.id = id;
        this.mainCategoryName = mainCategoryName;
    }
}
