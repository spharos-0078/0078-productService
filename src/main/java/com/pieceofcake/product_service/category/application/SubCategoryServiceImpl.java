package com.pieceofcake.product_service.category.application;

import com.pieceofcake.product_service.category.dto.in.CreateSubCategoryRequestDto;
import com.pieceofcake.product_service.category.infrastructure.MainCategoryRepository;
import com.pieceofcake.product_service.category.infrastructure.SubCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    @Transactional
    public void createSubCategory(CreateSubCategoryRequestDto createSubCategoryRequestDto) {
        subCategoryRepository.save(createSubCategoryRequestDto.toEntity());
    }
}