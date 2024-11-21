package com.api_franchises.api_franchises.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_franchises.api_franchises.entity.DTO.FranchiseDto;
import com.api_franchises.api_franchises.service.interfaces.FranchiseService;
import com.api_franchises.api_franchises.utils.ApiResponse;

@RestController
@RequestMapping("/franchise")
public class FranchiseController {
    @Autowired
    private FranchiseService service;

     @GetMapping("/all")
    public ResponseEntity<List<FranchiseDto>> getAll() {
        return ResponseEntity.ok(service.findAllFranchises());
    }

    @GetMapping("/{franchiseId}")
    public ResponseEntity<FranchiseDto> getFranchiseById(@PathVariable(name = "franchiseId") String franchiseId) {
        return ResponseEntity.ok(service.findById(franchiseId));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createFranchise(@RequestBody FranchiseDto franchiseDto) {
        return ResponseEntity.ok(service.save(franchiseDto));
    }


    @DeleteMapping("/delete/{franchiseId}")
    public ResponseEntity<ApiResponse> deleteFranchise(@PathVariable(name = "franchiseId") String franchiseId) {
        return ResponseEntity.ok(service.delete(franchiseId));
    }

   @PutMapping("/update/{franchiseId}")
   public ResponseEntity<ApiResponse> updateFranchise(@PathVariable(name = "franchiseId") String franchiseId, @RequestBody FranchiseDto franchiseDto) {
        return ResponseEntity.ok(service.update(franchiseId, franchiseDto));
   }
    @DeleteMapping("/delete-by-name/{name}")
    public ResponseEntity<ApiResponse> deleteFranchiseByName(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(service.deleteByName(name));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<FranchiseDto>> searchFranchise(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(service.findAllFranchisesByName(name));
    }
    
}
