package com.api_franchises.api_franchises.entity.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api_franchises.api_franchises.entity.model.Branch;

public interface BranchRepository extends MongoRepository<Branch, String> {
    List<Branch> findByName(String name);

    void deleteByName(String name);

    List<Branch> findAllBranchesByName(String name);
    
}
