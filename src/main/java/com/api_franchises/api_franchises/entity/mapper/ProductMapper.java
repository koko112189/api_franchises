package com.api_franchises.api_franchises.entity.mapper;

import com.api_franchises.api_franchises.entity.DTO.ProductDto;
import com.api_franchises.api_franchises.entity.model.Product;

public class ProductMapper {
      public static ProductDto toDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
    }
    public static Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(product.getId());
        product.setName(product.getName());
        product.setDescription(product.getDescription());
        product.setPrice(product.getPrice());
        product.setQuantity(product.getQuantity());
        return product;
    }
}
