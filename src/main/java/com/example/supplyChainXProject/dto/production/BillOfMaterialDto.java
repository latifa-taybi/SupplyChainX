package com.example.supplyChainXProject.dto.production;
import jakarta.validation.constraints.NotNull;


public record BillOfMaterialDto(
        @NotNull(message = "the quantity is required")
        Integer quantity,

        Long productId,
        Long rawMaterialId
){}
