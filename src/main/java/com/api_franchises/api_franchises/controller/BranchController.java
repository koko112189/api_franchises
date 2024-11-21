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

import com.api_franchises.api_franchises.entity.DTO.BranchDto;
import com.api_franchises.api_franchises.service.interfaces.BranchService;
import com.api_franchises.api_franchises.utils.ApiResponse;


@RestController
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    private BranchService service;

     @GetMapping("/all")
    public ResponseEntity<List<BranchDto>> getAll() {
        return ResponseEntity.ok(service.findAllBranchs());
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable(name = "branchId") String branchId) {
        return ResponseEntity.ok(service.findById(branchId));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createBranch( @RequestBody String franchiseId,@RequestBody BranchDto branchDto) {
        return ResponseEntity.ok(service.add(franchiseId,branchDto));
    }


    @DeleteMapping("/delete/{branchId}/{franchiseId}")
    public ResponseEntity<ApiResponse> deleteBranch(@PathVariable(name = "branchId") String branchId, @PathVariable(name = "franchiseId") String franchiseId) {
        return ResponseEntity.ok(service.delete(branchId, franchiseId));
    }

   @PutMapping("/update/{branchId}")
   public ResponseEntity<ApiResponse> updateBranch(@PathVariable(name = "franchiseId") String franchiseId, @PathVariable(name = "branchId") String branchId, @RequestBody BranchDto branchDto) {
        return ResponseEntity.ok(service.update( franchiseId,branchId, branchDto));
   }
}
