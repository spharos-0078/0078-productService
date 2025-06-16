package com.pieceofcake.product_service.category.application;

import com.pieceofcake.product_service.category.dto.in.CreateCategoryNameEventDto;
import com.pieceofcake.product_service.category.infrastructure.MainCategoryRepository;
import com.pieceofcake.product_service.category.infrastructure.SubCategoryRepository;
import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.common.exception.BaseException;
import com.pieceofcake.product_service.kafka.producer.CategoryKafkaProducer;
import com.pieceofcake.product_service.kafka.producer.event.CategoryNameEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryKafkaProducer categoryKafkaProducer;


    @Override
    public void createCategoryNameRead(CreateCategoryNameEventDto createCategoryNameEventDto) {
        String mainCategoryName = mainCategoryRepository.findById(createCategoryNameEventDto.getMainCategoryId()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY)
        ).getMainCategoryName();

        String subCategoryName = subCategoryRepository.findById(createCategoryNameEventDto.getSubCategoryId()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY)
        ).getSubCategoryName();

        CategoryNameEvent event = CategoryNameEvent.builder()
                .productUuid(createCategoryNameEventDto.getProductUuid())
                .mainCategoryName(mainCategoryName)
                .subCategoryName(subCategoryName)
                .build();

        categoryKafkaProducer.sendCategoryNameEvent(event);
    }
}
