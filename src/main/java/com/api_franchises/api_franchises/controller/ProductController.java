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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api_franchises.api_franchises.entity.DTO.ProductDto;
import com.api_franchises.api_franchises.service.interfaces.ProductService;
import com.api_franchises.api_franchises.utils.ApiResponse;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

     @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(service.findAllProducts());
    }

    @GetMapping("/{branchId}/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "branchId") String branchId, @PathVariable(name = "productId") String productId) {
        return ResponseEntity.ok(service.findById(branchId, productId));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody String franchiseId,@RequestBody String branchId,@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(service.add(franchiseId, branchId, productDto));
    }


    @DeleteMapping("/delete/{franchiseId}/{branchId}/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable(name = "franchiseId") String franchiseId, @PathVariable(name = "branchId") String branchId, @PathVariable(name = "productId") String productId) {
        return ResponseEntity.ok(service.delete(franchiseId, branchId, productId));
    }

   @PutMapping("/update/{franchiseId}/{branchId}/{productId}")
   public ResponseEntity<ApiResponse> updateProduct(@PathVariable(name = "franchiseId") String franchiseId, @PathVariable(name = "branchId") String branchId,@PathVariable(name = "productId") String productId, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(service.update(franchiseId, branchId, productId, productDto));
   }

    @PutMapping("/{franchiseId}/{branchId}/{productoId}/stock")
    public ResponseEntity<ApiResponse> updateStock(@PathVariable(name = "franchiseId") String franchiseId,@PathVariable(name = "branchId") String branchId, @PathVariable(name = "productId") String productId, @RequestParam int newStock) {
        return ResponseEntity.ok(service.updateStock(franchiseId, branchId, productId, newStock));
    }

    @GetMapping("/{franchiseId}/max-stock")
    public ResponseEntity<List<ProductDto>> getProductsWithLargerStock(@PathVariable(name = "franchiseId") String franchiseId) {
        List<ProductDto> products = service.getProductsWithLargerStock(franchiseId);
        return ResponseEntity.ok(products);
    }
    
}
