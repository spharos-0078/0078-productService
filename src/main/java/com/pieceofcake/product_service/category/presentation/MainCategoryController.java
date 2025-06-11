package com.pieceofcake.product_service.category.presentation;

import com.pieceofcake.product_service.category.application.MainCategoryServiceImpl;
import com.pieceofcake.product_service.category.dto.in.CreateMainCategoryRequestDto;
import com.pieceofcake.product_service.category.dto.in.UpdateMainCategoryRequestDto;
import com.pieceofcake.product_service.category.dto.out.GetMainCategoryResponseDto;
import com.pieceofcake.product_service.category.vo.in.CreateMainCategoryRequestVo;
import com.pieceofcake.product_service.category.vo.in.UpdateMainCategoryRequestVo;
import com.pieceofcake.product_service.category.vo.out.GetMainCategoryResponseVo;
import com.pieceofcake.product_service.common.entity.BaseResponseEntity;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/main-category")
@RequiredArgsConstructor
@RestController
public class MainCategoryController {

    private final MainCategoryServiceImpl mainCategoryService;

    @PostMapping
    public BaseResponseEntity<Void> createMainCategory(@RequestBody CreateMainCategoryRequestVo createMainCategoryRequestVo) {
        mainCategoryService.createMainCategory(CreateMainCategoryRequestDto.from(createMainCategoryRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @GetMapping("/list")
    public BaseResponseEntity<List<GetMainCategoryResponseVo>> getMainCategoryList() {
        return new BaseResponseEntity<>(mainCategoryService.getMainCategoryList()
                .stream().map(GetMainCategoryResponseDto::toVo).toList());
    }

    @GetMapping("/{mainCategoryId}")
    public BaseResponseEntity<GetMainCategoryResponseVo> getMainCategoryList(@PathVariable Integer mainCategoryId) {
        return new BaseResponseEntity<>(mainCategoryService.getMainCategory(mainCategoryId).toVo());
    }

    @PutMapping
    public BaseResponseEntity<Void> updateMainCategory(@RequestBody UpdateMainCategoryRequestVo updateMainCategoryRequestVo) {
        mainCategoryService.updateMainCategory(UpdateMainCategoryRequestDto.from(updateMainCategoryRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @DeleteMapping("/{mainCategoryId}")
    public BaseResponseEntity<Void> deleteMainCategory(@PathVariable Integer mainCategoryId) {
        mainCategoryService.deleteMainCategory(mainCategoryId);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}

