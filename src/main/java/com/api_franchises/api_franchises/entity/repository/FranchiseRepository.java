package com.api_franchises.api_franchises.entity.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.api_franchises.api_franchises.entity.model.Franchise;

public interface FranchiseRepository extends MongoRepository<Franchise, String> {
    List<Franchise> findByName(String name);

    void deleteByName(String name);

    List<Franchise> findAllFranchisessByName(String name);
}
