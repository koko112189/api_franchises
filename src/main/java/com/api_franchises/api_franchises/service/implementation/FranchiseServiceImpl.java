package com.api_franchises.api_franchises.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api_franchises.api_franchises.entity.DTO.FranchiseDto;
import com.api_franchises.api_franchises.entity.mapper.FranchiseMapper;
import com.api_franchises.api_franchises.entity.model.Franchise;
import com.api_franchises.api_franchises.entity.repository.FranchiseRepository;
import com.api_franchises.api_franchises.exception.FranchiseNotFoundException;
import com.api_franchises.api_franchises.service.interfaces.FranchiseService;
import com.api_franchises.api_franchises.utils.ApiResponse;
import com.api_franchises.api_franchises.utils.Message;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Override
    public FranchiseDto findById(String franchiseId) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new FranchiseNotFoundException(Message.FRANCHISE_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
        return FranchiseMapper.toDto(franchise);
    }

    @Override
    public List<FranchiseDto> findAllFranchises() {
        return franchiseRepository.findAll()
                .stream()
                .map((dto) -> new FranchiseDto(dto.getId(), dto.getName(), dto.getDescription()))
                .toList();
    }

    @Override
    public ApiResponse delete(String franchiseId) {
        FranchiseDto franchiseDto = findById(franchiseId);
        if (franchiseDto == null) {
            throw new FranchiseNotFoundException(Message.FRANCHISE_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now());
        }
        Franchise franchise = new Franchise();
        franchise.setId(franchiseId);
        franchise.setName(franchiseDto.name());
        franchise.setDescription(franchiseDto.description());
        franchiseRepository.delete(franchise);
        return new ApiResponse(Message.FRANCHISE_DELETE_SUCCESSFULLY, 204, HttpStatus.NO_CONTENT, LocalDateTime.now());
    }


    @Override
    public ApiResponse save(FranchiseDto dto) {
        Franchise franchise = new Franchise();
        franchise.setDescription(dto.description());
        franchise.setName(dto.name());
        franchiseRepository.save(franchise);
        return new ApiResponse(Message.FRANCHISE_SAVE_SUCCESSFULLY, 201, HttpStatus.CREATED, LocalDateTime.now());
    }

    @Override
    public ApiResponse update(String franchiseId, FranchiseDto dto) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new FranchiseNotFoundException(Message.FRANCHISE_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
        franchise.setDescription(dto.description());
        franchise.setName(dto.name());
        franchiseRepository.save(franchise);
        return new ApiResponse(Message.FRANCHISE_UPDATE_SUCCESSFULLY, 201, HttpStatus.OK, LocalDateTime.now());
    }

    @Override
    public ApiResponse deleteByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        franchiseRepository.deleteByName(name);
        return new ApiResponse(Message.FRANCHISE_DELETE_SUCCESSFULLY, 204, HttpStatus.NO_CONTENT, LocalDateTime.now());
    }

    @Override
    public List<FranchiseDto> findAllFranchisesByName(String name) {
        List<Franchise> franchises = franchiseRepository.findAllFranchisessByName(name);
        if (!franchises.isEmpty()) {
            return franchises.stream().map((dto) -> new FranchiseDto(dto.getId(), dto.getName(), dto.getDescription())).toList();
        }
        throw new FranchiseNotFoundException(Message.FRANCHISE_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now());
    }
    
}
