package com.pieceofcake.product_service.category.application;

import com.pieceofcake.product_service.category.dto.in.CreateSubCategoryRequestDto;
import com.pieceofcake.product_service.category.dto.in.UpdateSubCategoryRequestDto;
import com.pieceofcake.product_service.category.dto.out.GetSubCategoryResponseDto;
import com.pieceofcake.product_service.category.entity.SubCategory;
import com.pieceofcake.product_service.category.infrastructure.SubCategoryRepository;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.common.exception.BaseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;

    @Override
    @Transactional
    public void createSubCategory(CreateSubCategoryRequestDto createSubCategoryRequestDto) {
        subCategoryRepository.save(createSubCategoryRequestDto.toEntity());
    }

    @Override
    public List<GetSubCategoryResponseDto> getSubCategoryList() {
        return subCategoryRepository.findAll().stream().map(GetSubCategoryResponseDto::from).toList();
    }

    @Override
    public GetSubCategoryResponseDto getSubCategory(Integer subCategoryId) {
        return GetSubCategoryResponseDto.from(subCategoryRepository.findById(subCategoryId).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY)
        ));
    }

    @Override
    public void updateSubCategory(UpdateSubCategoryRequestDto updateSubCategoryRequestDto) {
        SubCategory subCategory = subCategoryRepository.findById(updateSubCategoryRequestDto.getId()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY)
        );

        subCategoryRepository.save(updateSubCategoryRequestDto.toEntity(subCategory));
    }

    @Override
    public void deleteSubCategory(Integer subCategoryId) {
        subCategoryRepository.deleteById(subCategoryId);
    }

    @Override
    public List<GetSubCategoryResponseDto> getSubCategoryListByMainCategoryId(Integer mainCategoryId) {
        return subCategoryRepository.findAllByMainCategoryId(mainCategoryId)
                .stream().map(GetSubCategoryResponseDto::from).toList();
    }
}