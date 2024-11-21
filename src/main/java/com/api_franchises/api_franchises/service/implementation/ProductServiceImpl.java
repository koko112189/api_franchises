package com.api_franchises.api_franchises.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api_franchises.api_franchises.entity.DTO.ProductDto;
import com.api_franchises.api_franchises.entity.mapper.ProductMapper;
import com.api_franchises.api_franchises.entity.model.Branch;
import com.api_franchises.api_franchises.entity.model.Franchise;
import com.api_franchises.api_franchises.entity.model.Product;
import com.api_franchises.api_franchises.entity.repository.FranchiseRepository;
import com.api_franchises.api_franchises.exception.BranchException;
import com.api_franchises.api_franchises.exception.ProductException;
import com.api_franchises.api_franchises.service.interfaces.ProductService;
import com.api_franchises.api_franchises.utils.ApiResponse;
import com.api_franchises.api_franchises.utils.Message;

@Service
public class ProductServiceImpl implements ProductService {
     @Autowired
    private FranchiseRepository franchiseRepository;

    @Override
    public ProductDto findById(String branchId, String productId) {

         Branch branchFound = franchiseRepository.findAll().stream()
                .flatMap(franchise -> franchise.getBranchs().stream())
                .filter(branch -> branch.getId().equals(branchId))
                .findFirst()
                .orElseThrow(() -> new ProductException(Message.BRANCH_NOT_FOUND, 404, HttpStatus.NOT_FOUND,
                        LocalDateTime.now()));

        Product productFound = branchFound.getProducts().stream().filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductException(Message.PRODUCT_NOT_FOUND, 404, HttpStatus.NOT_FOUND,
                        LocalDateTime.now()));
        return ProductMapper.toDto(productFound);
    }

    @Override
    public ApiResponse add(String franchiseId, String branchId, ProductDto dto) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new BranchException(Message.FRANCHISE_NOT_FOUND, 404,
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));

        Branch branch = franchise.getBranchs().stream().filter(branch1 -> branch1.getId().equals(branchId))
                .findFirst()
                .orElseThrow(() -> new BranchException(Message.BRANCH_NOT_FOUND, 404,
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));

        Product product = ProductMapper.toEntity(dto);
        branch.getProducts().add(product);
        franchiseRepository.save(franchise);
        return new ApiResponse(Message.PRODUCT_SAVE_SUCCESSFULLY, 201, HttpStatus.CREATED, LocalDateTime.now());
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Franchise> franchises = franchiseRepository.findAll();
        List<Branch> branchs = franchises.stream().flatMap(franchise -> franchise.getBranchs().stream()).collect(Collectors.toList());
        List<Product> products = branchs.stream().flatMap(branch -> branch.getProducts().stream()).collect(Collectors.toList());
        return products.stream().map(ProductMapper::toDto).toList();
    }

    @Override
    public ApiResponse delete(String franchiseId, String branchId, String productId) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new BranchException(Message.FRANCHISE_NOT_FOUND, 404,
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));

        Branch branch = franchise.getBranchs().stream().filter(branch1 -> branch1.getId().equals(branchId))
                .findFirst()
                .orElseThrow(() -> new BranchException(Message.BRANCH_NOT_FOUND, 404,
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));

        boolean removed = branch.getProducts().removeIf(product -> product.getId().equals(productId));
        if (!removed) {
            throw new BranchException(Message.BRANCH_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now());
        }

        franchiseRepository.save(franchise);
        return new ApiResponse(Message.PRODUCT_DELETE_SUCCESSFULLY, 204, HttpStatus.NO_CONTENT, LocalDateTime.now());
    }

    @Override
    public ApiResponse update(String franchiseId, String branchId, String productId, ProductDto dto) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new BranchException(Message.FRANCHISE_NOT_FOUND, 404,
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));

        Branch branchFound = franchise.getBranchs().stream()
                .filter(branch -> branch.getId().equals(branchId))
                .findFirst()
                .orElseThrow(() -> new BranchException(Message.BRANCH_NOT_FOUND, 404,
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));
       
        Product productFound = branchFound.getProducts().stream().filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductException(Message.PRODUCT_NOT_FOUND, 404, HttpStatus.NOT_FOUND,
                        LocalDateTime.now()));
        
        productFound.setName(dto.name());
        productFound.setDescription(dto.description());
        productFound.setPrice(dto.price());
        productFound.setQuantity(dto.quantity());

        franchiseRepository.save(franchise);
        return new ApiResponse(Message.PRODUCT_UPDATE_SUCCESSFULLY, 201, HttpStatus.OK, LocalDateTime.now());
    }

    @Override
    public ApiResponse updateStock(String franchiseId, String branchId, String productId, Integer quantity) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new BranchException(Message.FRANCHISE_NOT_FOUND, 404,
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));

        Branch branchFound = franchise.getBranchs().stream()
                .filter(branch -> branch.getId().equals(branchId))
                .findFirst()
                .orElseThrow(() -> new BranchException(Message.BRANCH_NOT_FOUND, 404,
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));

        Product productFound = branchFound.getProducts().stream().filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductException(Message.PRODUCT_NOT_FOUND, 404, HttpStatus.NOT_FOUND,
                        LocalDateTime.now()));

        productFound.setQuantity(quantity);

        franchiseRepository.save(franchise);
        return new ApiResponse(Message.PRODUCT_UPDATE_SUCCESSFULLY, 201, HttpStatus.OK, LocalDateTime.now());
    }

    @Override
    public List<ProductDto> getProductsWithLargerStock(String franchiseId) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new BranchException(Message.FRANCHISE_NOT_FOUND, 404,
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));

        List<Product> products = franchise.getBranchs().stream()
            .map(branch -> branch.getProducts().stream()
            .max((p1,p2) -> 
            Integer.compare(p1.getQuantity(), p2.getQuantity()))
            .orElse(null)).filter(product -> product != null)
            .collect(Collectors.toList());

        return products.stream().map(ProductMapper::toDto).toList();
    }
    
}
