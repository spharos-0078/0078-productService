package com.pieceofcake.product_service.category.application;

import com.pieceofcake.product_service.category.dto.in.CreateProductCategoryRequestDto;
import com.pieceofcake.product_service.category.infrastructure.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
    private final ProductCategoryRepository productCategoryRepository;

    @Transactional
    @Override
    public void createProductCategory(CreateProductCategoryRequestDto createProductCategoryRequestDto) {
        productCategoryRepository.save(createProductCategoryRequestDto.toEntity());
    }
}
