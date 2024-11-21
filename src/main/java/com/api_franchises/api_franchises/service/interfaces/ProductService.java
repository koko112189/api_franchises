package com.api_franchises.api_franchises.service.interfaces;

import java.util.List;

import com.api_franchises.api_franchises.entity.DTO.ProductDto;
import com.api_franchises.api_franchises.utils.ApiResponse;

public interface  ProductService {
    ProductDto findById(String branchId, String productId);

    List<ProductDto> findAllProducts();

    ApiResponse add(String franchiseId, String branchId,ProductDto dto);

    ApiResponse delete(String franchiseId, String branchId, String productId);

    ApiResponse update(String franchiseId, String branchId, String productId, ProductDto dto);

    ApiResponse updateStock(String franchiseId, String branchId, String productId, Integer quantity);

    List<ProductDto> getProductsWithLargerStock(String franchiseId);
}
