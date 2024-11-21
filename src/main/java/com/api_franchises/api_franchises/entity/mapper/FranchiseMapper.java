package com.api_franchises.api_franchises.entity.mapper;

import com.api_franchises.api_franchises.entity.DTO.FranchiseDto;
import com.api_franchises.api_franchises.entity.model.Franchise;

public class FranchiseMapper {
     public static FranchiseDto toDto(Franchise franchise) {
        return new FranchiseDto(franchise.getId(), franchise.getName(), franchise.getDescription());
    }
    public static Franchise toEntity(FranchiseDto franchiseDto) {
        Franchise franchise = new Franchise();
        franchise.setId(franchise.getId());
        franchise.setName(franchise.getName());
        franchise.setDescription(franchise.getDescription());
        franchise.setBranchs(franchise.getBranchs());
        return franchise;
    }
}
