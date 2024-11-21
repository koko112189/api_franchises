package com.api_franchises.api_franchises.service.interfaces;

import java.util.List;

import com.api_franchises.api_franchises.entity.DTO.BranchDto;
import com.api_franchises.api_franchises.utils.ApiResponse;

public interface BranchService {
    BranchDto findById(String franchiseId);

    List<BranchDto> findAllBranchs();

    ApiResponse add(String franchiseId,BranchDto branchDto);

    ApiResponse delete(String branchId, String franchiseId);

    ApiResponse update(String franchiseId,String branchId, BranchDto dto);
}
