package com.example.supplyChainXProject.controller.production;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.SupplierDto;
import com.example.supplyChainXProject.dto.approvisionnement.SupplyOrderDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierOrder.SupplyOrderDtoResponse;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse.SupplierDtoResponse;
import com.example.supplyChainXProject.dto.production.ProductDto;
import com.example.supplyChainXProject.dto.production.response.ProductResponseDto;
import com.example.supplyChainXProject.service.production.interfaces.IProductService;
import com.example.supplyChainXProject.utils.Validation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "product crud", description = "crud")
@RequestMapping("/api/products")
public class ProductController {
    private final IProductService productService;
    @PostMapping
    @PreAuthorize("hasRole('CHEF_PRODUCTION')")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @GetMapping
    @PreAuthorize("hasRole('SUPERVISEUR_PRODUCTION')")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        List<ProductResponseDto> produducts = productService.getAllProducts();
        return ResponseEntity.ok(produducts);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CHEF_PRODUCTION')")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CHEF_PRODUCTION')")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductDto productDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        ProductResponseDto productResponseDto = productService.updateProduct(productDto, id);
        return ResponseEntity.ok(productResponseDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CHEF_PRODUCTION')")
    public ResponseEntity<MessageResponse> deleteProduct(@PathVariable("id") Long id){
        MessageResponse messageResponse = productService.deleteProduct(id);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/search/{name}")
    @PreAuthorize("hasRole('SUPERVISEUR_PRODUCTION')")
    public ResponseEntity<?> searchProductByName(@PathVariable("name") String name) {
        ProductResponseDto product = productService.searchByName(name);
        return ResponseEntity.ok(product);
    }


}