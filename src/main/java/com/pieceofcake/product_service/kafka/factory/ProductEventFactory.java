package com.pieceofcake.product_service.kafka.factory;

import com.pieceofcake.product_service.category.entity.MainCategory;
import com.pieceofcake.product_service.category.entity.ProductCategory;
import com.pieceofcake.product_service.category.entity.SubCategory;
import com.pieceofcake.product_service.category.infrastructure.MainCategoryRepository;
import com.pieceofcake.product_service.category.infrastructure.ProductCategoryRepository;
import com.pieceofcake.product_service.category.infrastructure.SubCategoryRepository;
import com.pieceofcake.product_service.kafka.dto.ProductImageDto;
import com.pieceofcake.product_service.kafka.event.ProductEvent;
import com.pieceofcake.product_service.product.entity.Product;
import com.pieceofcake.product_service.product.entity.ProductImage;
import com.pieceofcake.product_service.product.infrastructure.ProductImageRepository;
import com.pieceofcake.product_service.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductEventFactory {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductEvent createEvent(String productUuid) {
        Product product = productRepository.findByProductUuid(productUuid)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<ProductImage> imageList = productImageRepository.findAllByProductUuid(productUuid);
        ProductCategory category = productCategoryRepository.findByProductUuid(productUuid);
        MainCategory mainCategory = mainCategoryRepository.findById(category.getMainCategoryId()).get();
        SubCategory subCategory = subCategoryRepository.findById(category.getSubCategoryId()).get();

        return ProductEvent.builder()
                .productUuid(product.getProductUuid())
                .productName(product.getProductName())
                .aiEstimatedPrice(product.getAiEstimatedPrice())
                .purchasePrice(product.getPurchasePrice())
                .productStatus(product.getProductStatus())
                .storageLocation(product.getStorageLocation())
                .description(product.getDescription())
                .images(imageList.stream()
                        .map(ProductImageDto::from)
                        .toList())
                .mainCategoryName(mainCategory.getMainCategoryName())
                .subCategoryName(subCategory.getSubCategoryName())
                .build();
    }
}

