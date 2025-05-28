package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.dto.in.ProductCategoryCreateRequestDto;
import com.pieceofcake.product_service.infrastructure.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
    private final ProductCategoryRepository productCategoryRepository;

    @Transactional
    @Override
    public void createProductCategory(ProductCategoryCreateRequestDto productCategoryCreateRequestDto) {
        productCategoryRepository.save(productCategoryCreateRequestDto.toEntity());
    }
}
