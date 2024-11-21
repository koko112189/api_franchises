package com.api_franchises.api_franchises.entity.mapper;

import com.api_franchises.api_franchises.entity.DTO.BranchDto;
import com.api_franchises.api_franchises.entity.model.Branch;

public class BranchMapper {
      public static BranchDto toDto(Branch branch) {
        return new BranchDto(branch.getId(), branch.getName(), branch.getDescription());
    }

    public static Branch toEntity(BranchDto branchDto) {
        Branch branch = new Branch();
        branch.setId(branch.getId());
        branch.setName(branch.getName());
        branch.setDescription(branch.getDescription());
        branch.setProducts(branch.getProducts());
        return branch;
    }
}
