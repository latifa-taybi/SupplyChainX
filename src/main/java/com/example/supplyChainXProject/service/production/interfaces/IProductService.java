package com.example.supplyChainXProject.service.production.interfaces;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.production.ProductDto;
import com.example.supplyChainXProject.dto.production.response.ProductResponseDto;

import java.util.List;

public interface IProductService {
    ProductResponseDto createProduct(ProductDto productDto);
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto updateProduct(ProductDto productDto, Long id);
    ProductResponseDto getProductById(Long id);
    MessageResponse deleteProduct(Long id);
    ProductResponseDto searchByName(String name);
}
