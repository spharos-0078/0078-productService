package com.pieceofcake.product_service.application;

import com.pieceofcake.product_service.common.entity.BaseResponseStatus;
import com.pieceofcake.product_service.common.exception.BaseException;
import com.pieceofcake.product_service.dto.in.ProductCategoryCreateRequestDto;
import com.pieceofcake.product_service.dto.in.ProductCreateRequestDto;
import com.pieceofcake.product_service.dto.in.ProductImageCreateRequestDto;
import com.pieceofcake.product_service.dto.in.ProductUpdateRequestDto;
import com.pieceofcake.product_service.dto.out.ProductGetUuidResponseDto;
import com.pieceofcake.product_service.entity.Product;
import com.pieceofcake.product_service.entity.ProductStatus;
import com.pieceofcake.product_service.infrastructure.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImageServiceImpl productImageService;
    private final ProductCategoryServiceImpl productCategoryService;

    @Transactional
    @Override
    public void createProduct(ProductCreateRequestDto productCreateRequestDto) {
        String productUuid = UUID.randomUUID().toString();
        productRepository.save(productCreateRequestDto.toEntity(productUuid));

        productImageService.createProductImage(ProductImageCreateRequestDto.of(
                productUuid, productCreateRequestDto.getProductImageRequestDtoList()));

        productCategoryService.createProductCategory(
                ProductCategoryCreateRequestDto.of(productUuid,
                        productCreateRequestDto.getMainCategoryId(), productCreateRequestDto.getSubCategoryId()));
    }

    @Transactional
    @Override
    public void updateProduct(ProductUpdateRequestDto productUpdateRequestDto) {
        Product product = productRepository.findByProductUuid(productUpdateRequestDto.getProductUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));

        productRepository.save(productUpdateRequestDto.toEntity(product));
    }

    @Override
    public List<ProductGetUuidResponseDto> getProductUuidList() {
        return productRepository.findAllByProductStatus(ProductStatus.STORED)
                .stream().map(ProductGetUuidResponseDto::from).toList();
    }

    @Transactional
    @Override
    public void deleteProduct(String productUuid) {
        productRepository.softDeleteByProductUuid(productUuid);
    }
}
