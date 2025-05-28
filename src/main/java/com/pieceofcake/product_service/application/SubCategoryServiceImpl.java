package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.common.exception.BaseException;
import com.pieceofcake.product_service.dto.in.SubCategoryCreateRequestDto;
import com.pieceofcake.product_service.infrastructure.MainCategoryRepository;
import com.pieceofcake.product_service.infrastructure.SubCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubCategoryServiceImpl implements SubCategoryService{
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    @Transactional
    public void createSubCategory(SubCategoryCreateRequestDto subCategoryCreateRequestDto) {
        if (!mainCategoryRepository.existsById(subCategoryCreateRequestDto.getMainCategoryId()))
            throw new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY);

        subCategoryRepository.save(subCategoryCreateRequestDto.toEntity());
    }
}
