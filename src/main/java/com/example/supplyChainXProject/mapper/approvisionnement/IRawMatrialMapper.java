package com.example.supplyChainXProject.mapper.approvisionnement;

import com.example.supplyChainXProject.dto.approvisionnement.RawMaterialDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.rawMaterialResponse.RawMaterialDtoResponse;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse.RawMaterialDtoResponseSansList;
import com.example.supplyChainXProject.entity.approvisionnement.RawMaterial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = SupplierMapper.class)
public interface IRawMatrialMapper {
    RawMaterialDtoResponse toDtoResponse(RawMaterial rawMaterial);
    RawMaterialDto toDTO(RawMaterial rawMaterial);
    RawMaterial toEntity(RawMaterialDto rawMaterialDto);
    RawMaterialDtoResponseSansList toSansList(RawMaterial rawMaterial);
}
