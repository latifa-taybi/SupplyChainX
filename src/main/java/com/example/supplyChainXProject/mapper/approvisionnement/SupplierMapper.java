package com.example.supplyChainXProject.mapper.approvisionnement;

import com.example.supplyChainXProject.dto.approvisionnement.SupplierDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.rawMaterialResponse.SupplierDtoResponseSansList;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse.SupplierDtoResponse;
import com.example.supplyChainXProject.entity.approvisionnement.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierDto toDTO(Supplier supplier);
    Supplier toEntity(SupplierDto supplierDto);
    SupplierDtoResponse toDtoResponse(Supplier supplier);
    SupplierDtoResponseSansList toResponseSansList(Supplier supplier);
}
