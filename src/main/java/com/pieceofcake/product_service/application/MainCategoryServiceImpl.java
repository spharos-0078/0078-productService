package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.dto.in.MainCategoryCreateRequestDto;
import com.pieceofcake.product_service.infrastructure.MainCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MainCategoryServiceImpl implements MainCategoryService{

    private final MainCategoryRepository mainCategoryRepository;

    @Override
    @Transactional
    public void createMainCategory(MainCategoryCreateRequestDto mainCategoryCreateRequestDto) {
        mainCategoryRepository.save(mainCategoryCreateRequestDto.toEntity());
    }
}
