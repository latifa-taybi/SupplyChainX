package com.example.supplyChainXProject.mapper.approvisionnement;

import com.example.supplyChainXProject.dto.approvisionnement.SupplyOrderDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierOrder.SupplyOrderDtoResponse;
import com.example.supplyChainXProject.entity.approvisionnement.SupplyOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {SupplierMapper.class, IRawMaterialSupplyOrderMapper.class})
public interface ISupplyOrderMapper {
    SupplyOrderDtoResponse toDtoResponse(SupplyOrder supplyOrder);
}