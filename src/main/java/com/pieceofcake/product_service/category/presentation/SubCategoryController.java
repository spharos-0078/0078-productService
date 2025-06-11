package com.pieceofcake.product_service.category.presentation;

import com.pieceofcake.product_service.category.application.SubCategoryServiceImpl;
import com.pieceofcake.product_service.category.dto.in.CreateSubCategoryRequestDto;
import com.pieceofcake.product_service.category.dto.in.UpdateSubCategoryRequestDto;
import com.pieceofcake.product_service.category.dto.out.GetSubCategoryResponseDto;
import com.pieceofcake.product_service.category.vo.in.CreateSubCategoryRequestVo;
import com.pieceofcake.product_service.category.vo.in.UpdateSubCategoryRequestVo;
import com.pieceofcake.product_service.category.vo.out.GetSubCategoryResponseVo;
import com.pieceofcake.product_service.common.entity.BaseResponseEntity;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/sub-category")
@RequiredArgsConstructor
@RestController
public class SubCategoryController {
    private final SubCategoryServiceImpl subCategoryService;

    @PostMapping
    public BaseResponseEntity<Void> createSubCategory(
            @RequestBody CreateSubCategoryRequestVo createSubCategoryRequestVo) {
        subCategoryService.createSubCategory(CreateSubCategoryRequestDto.from(createSubCategoryRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @GetMapping("/list")
    public BaseResponseEntity<List<GetSubCategoryResponseVo>> getSubCategoryList() {
        return new BaseResponseEntity<>(subCategoryService.getSubCategoryList()
                .stream().map(GetSubCategoryResponseDto::toVo).toList());
    }

    @GetMapping("/{subCategoryId}")
    public BaseResponseEntity<GetSubCategoryResponseVo> getSubCategoryList(@PathVariable Integer subCategoryId) {
        return new BaseResponseEntity<>(subCategoryService.getSubCategory(subCategoryId).toVo());
    }

    @GetMapping("/list/{mainCategoryId}")
    public BaseResponseEntity<List<GetSubCategoryResponseVo>> getSubCategoryListByMainCategoryId(
            @PathVariable Integer mainCategoryId
    ) {
        return new BaseResponseEntity<>(subCategoryService.getSubCategoryListByMainCategoryId(mainCategoryId)
                .stream().map(GetSubCategoryResponseDto::toVo).toList());
    }

    @PutMapping
    public BaseResponseEntity<Void> updateSubCategory(@RequestBody UpdateSubCategoryRequestVo updateSubCategoryRequestVo) {
        subCategoryService.updateSubCategory(UpdateSubCategoryRequestDto.from(updateSubCategoryRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @DeleteMapping("/{subCategoryId}")
    public BaseResponseEntity<Void> deleteSubCategory(@PathVariable Integer subCategoryId) {
        subCategoryService.deleteSubCategory(subCategoryId);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
