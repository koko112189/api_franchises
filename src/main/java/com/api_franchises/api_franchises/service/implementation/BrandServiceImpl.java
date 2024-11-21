package com.api_franchises.api_franchises.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api_franchises.api_franchises.entity.DTO.BranchDto;
import com.api_franchises.api_franchises.entity.mapper.BranchMapper;
import com.api_franchises.api_franchises.entity.model.Branch;
import com.api_franchises.api_franchises.entity.model.Franchise;
import com.api_franchises.api_franchises.entity.repository.FranchiseRepository;
import com.api_franchises.api_franchises.exception.BranchException;
import com.api_franchises.api_franchises.service.interfaces.BranchService;
import com.api_franchises.api_franchises.utils.ApiResponse;
import com.api_franchises.api_franchises.utils.Message;

@Service
public class BrandServiceImpl implements BranchService {
    @Autowired
    private FranchiseRepository franchiseRepository;

    @Override
    public BranchDto findById(String branchId) {
        Branch branchFound = franchiseRepository.findAll().stream()
                .flatMap(franchise -> franchise.getBranchs().stream())
                .filter(branch -> branch.getId().equals(branchId))
                .findFirst()
                .orElseThrow(() -> new BranchException(Message.BRANCH_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
                return  BranchMapper.toDto(branchFound);
    }

    @Override
    public ApiResponse add(String franchiseId, BranchDto branchDto) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new BranchException(Message.FRANCHISE_NOT_FOUND, 404,
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));

        franchise.getBranchs().add(BranchMapper.toEntity(branchDto));

        franchiseRepository.save(franchise);

        return new ApiResponse(Message.BRANCH_SAVE_SUCCESSFULLY, 201, HttpStatus.CREATED, LocalDateTime.now());
    }

    @Override
    public List<BranchDto> findAllBranchs() {
        List<Franchise> franchises = franchiseRepository.findAll();
        List<Branch> branchs = franchises.stream().flatMap(franchise -> franchise.getBranchs().stream()).collect(Collectors.toList());
        return branchs.stream().map(BranchMapper::toDto).toList();
    }

    @Override
    public ApiResponse delete(String franchiseId, String branchId) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new BranchException(Message.FRANCHISE_NOT_FOUND, 404,
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));

        boolean removed = franchise.getBranchs().removeIf(branch -> branch.getId().equals(branchId));

        if (!removed) {
            throw new BranchException(Message.BRANCH_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now());
        }

        franchiseRepository.save(franchise);

        return new ApiResponse(Message.BRANCH_DELETE_SUCCESSFULLY, 204, HttpStatus.NO_CONTENT, LocalDateTime.now());
    }

    @Override
    public ApiResponse update(String franchiseId,String branchId, BranchDto dto) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new BranchException(Message.FRANCHISE_NOT_FOUND, 404,
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));

        Branch branchFound = franchise.getBranchs().stream()
                .filter(branch -> branch.getId().equals(branchId))
                .findFirst()
                .orElseThrow(() -> new BranchException(Message.BRANCH_NOT_FOUND, 404,
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));
        branchFound.setName(dto.name());
        branchFound.setDescription(dto.description());

        franchiseRepository.save(franchise);

        return new ApiResponse(Message.BRANCH_UPDATE_SUCCESSFULLY, 204, HttpStatus.NO_CONTENT, LocalDateTime.now());
    }
}
