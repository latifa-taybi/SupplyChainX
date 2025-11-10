package com.example.supplyChainXProject.mapper.production;

import com.example.supplyChainXProject.dto.production.ProductionOrderDto;
import com.example.supplyChainXProject.dto.production.response.ProductionOrderDtoResponse;
import com.example.supplyChainXProject.entity.production.ProductionOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductionOrderMapper {
    ProductionOrderDtoResponse toDtoResponse(ProductionOrder productionOrder);
    ProductionOrder toEntity(ProductionOrderDto productionOrderDto);
}
