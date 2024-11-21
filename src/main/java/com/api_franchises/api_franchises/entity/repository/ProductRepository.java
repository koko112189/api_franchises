package com.api_franchises.api_franchises.entity.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api_franchises.api_franchises.entity.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByName(String name);

    void deleteByName(String name);

    List<Product> findAllProductsByName(String name);
}
