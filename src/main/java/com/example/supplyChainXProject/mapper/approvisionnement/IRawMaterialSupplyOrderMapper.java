package com.example.supplyChainXProject.mapper.approvisionnement;

import com.example.supplyChainXProject.dto.approvisionnement.RawMaterialSupplyOrderDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierOrder.RawMaterialSupplyOrderDtoResponse;
import com.example.supplyChainXProject.entity.approvisionnement.RawMaterialSupplyOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = IRawMatrialMapper.class)
public interface IRawMaterialSupplyOrderMapper {
    RawMaterialSupplyOrderDtoResponse toDtoResponse(RawMaterialSupplyOrder rawMaterialSupplyOrder);
}
