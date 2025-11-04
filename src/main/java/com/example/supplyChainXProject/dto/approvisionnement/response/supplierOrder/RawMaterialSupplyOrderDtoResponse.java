package com.example.supplyChainXProject.dto.approvisionnement.response.supplierOrder;

import com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse.RawMaterialDtoResponseSansList;

public record RawMaterialSupplyOrderDtoResponse(
        Long id,
        Integer quantity,
        RawMaterialDtoResponseSansList rawMaterial
) {}
