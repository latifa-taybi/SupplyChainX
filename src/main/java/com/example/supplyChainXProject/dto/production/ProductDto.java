package com.example.supplyChainXProject.dto.production;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

public record ProductDto(
        @NotEmpty(message = "name is required")
        String name,
        @NotNull(message = "time is required")
        Integer productionTime,
        @NotNull(message = "the cost is required")
        Double cost,
        @NotNull(message = "the stock is required")
        Integer stock,
        Map<Long, Integer> rawMaterials
){}
