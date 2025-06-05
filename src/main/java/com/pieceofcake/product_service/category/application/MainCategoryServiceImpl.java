package com.pieceofcake.product_service.category.application;

import com.pieceofcake.product_service.category.dto.in.CreateMainCategoryRequestDto;
import com.pieceofcake.product_service.category.dto.in.UpdateMainCategoryRequestDto;
import com.pieceofcake.product_service.category.dto.out.GetMainCategoryResponseDto;
import com.pieceofcake.product_service.category.entity.MainCategory;
import com.pieceofcake.product_service.category.infrastructure.MainCategoryRepository;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.common.exception.BaseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MainCategoryServiceImpl implements MainCategoryService {

    private final MainCategoryRepository mainCategoryRepository;

    @Override
    @Transactional
    public void createMainCategory(CreateMainCategoryRequestDto createMainCategoryRequestDto) {
        mainCategoryRepository.save(createMainCategoryRequestDto.toEntity());
    }

    @Override
    public List<GetMainCategoryResponseDto> getMainCategoryList() {
        return mainCategoryRepository.findAll().stream().map(GetMainCategoryResponseDto::from).toList();
    }

    @Override
    public GetMainCategoryResponseDto getMainCategory(Integer mainCategoryId) {
        return GetMainCategoryResponseDto.from(mainCategoryRepository.findById(mainCategoryId).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY)
        ));
    }

    @Override
    public void updateMainCategory(UpdateMainCategoryRequestDto updateMainCategoryRequestDto) {
        MainCategory mainCategory = mainCategoryRepository.findById(updateMainCategoryRequestDto.getId()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY)
        );

        mainCategoryRepository.save(updateMainCategoryRequestDto.toEntity(mainCategory));
    }

    @Override
    public void deleteMainCategory(Integer mainCategoryId) {
        mainCategoryRepository.deleteById(mainCategoryId);
    }
}
