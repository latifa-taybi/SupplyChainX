package com.example.supplyChainXProject.dto.production.response;


import com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse.RawMaterialDtoResponseSansList;

public record BillOfMaterialResponseDto(
        Integer quantity,
        RawMaterialDtoResponseSansList rawMaterial
){}
