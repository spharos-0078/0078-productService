package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.dto.in.SubCategoryCreateRequestDto;
import com.pieceofcake.product_service.infrastructure.MainCategoryRepository;
import com.pieceofcake.product_service.infrastructure.SubCategoryRepository;
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
    public void createSubCategory(SubCategoryCreateRequestDto subCategoryCreateRequestDto) {
        subCategoryRepository.save(subCategoryCreateRequestDto.toEntity());
    }
}