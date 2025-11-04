package com.example.supplyChainXProject.mapper.production;

import com.example.supplyChainXProject.dto.production.ProductDto;
import com.example.supplyChainXProject.dto.production.response.ProductResponseDto;
import com.example.supplyChainXProject.entity.production.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = IBillOfMaterialMapper.class)
public interface IProductMapper {
    Product toEntity(ProductDto productDto);
    ProductDto toDto(Product product);
    ProductResponseDto toDtoResponse(Product product);

}
