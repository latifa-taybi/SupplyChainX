package com.example.supplyChainXProject.dto.production.response;

import com.example.supplyChainXProject.dto.production.BillOfMaterialDto;

import java.util.List;

public record ProductResponseDto(
        String name,
        Integer productionTime,
        Double cost,
        Integer stock,
        List<BillOfMaterialResponseDto> billOfMaterials
){}
