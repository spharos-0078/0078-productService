package com.pieceofcake.product_service.category.application;

import com.pieceofcake.product_service.category.dto.in.CreateMainCategoryRequestDto;
import com.pieceofcake.product_service.category.infrastructure.MainCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MainCategoryServiceImpl implements MainCategoryService{

    private final MainCategoryRepository mainCategoryRepository;

    @Override
    @Transactional
    public void createMainCategory(CreateMainCategoryRequestDto createMainCategoryRequestDto) {
        mainCategoryRepository.save(createMainCategoryRequestDto.toEntity());
    }
}
