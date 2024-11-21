package com.api_franchises.api_franchises.service.interfaces;

import java.util.List;

import com.api_franchises.api_franchises.entity.DTO.FranchiseDto;
import com.api_franchises.api_franchises.utils.ApiResponse;

public interface FranchiseService {
     FranchiseDto findById(String franchiseId);

    List<FranchiseDto> findAllFranchises();

    ApiResponse delete(String franchiseId);

    ApiResponse save(FranchiseDto dto);

    ApiResponse update(String franchiseId, FranchiseDto dto);

    ApiResponse deleteByName(String name);

    List<FranchiseDto> findAllFranchisesByName(String name);
}
