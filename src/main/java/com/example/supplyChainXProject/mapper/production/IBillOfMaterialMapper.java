package com.example.supplyChainXProject.mapper.production;

import com.example.supplyChainXProject.dto.production.BillOfMaterialDto;
import com.example.supplyChainXProject.dto.production.response.BillOfMaterialResponseDto;
import com.example.supplyChainXProject.entity.production.BillOfMaterial;
import com.example.supplyChainXProject.mapper.approvisionnement.IRawMatrialMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = IRawMatrialMapper.class)
public interface IBillOfMaterialMapper {
    BillOfMaterialResponseDto toDtoResponse(BillOfMaterial billOfMaterial);
}
