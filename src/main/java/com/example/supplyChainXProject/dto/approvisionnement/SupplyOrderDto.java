package com.example.supplyChainXProject.dto.approvisionnement;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Map;

public record SupplyOrderDto (
    @NotNull(message = "Supplier ID cannot be null")
    Long supplierId,

    @NotEmpty(message = "Order must have at least one item")
    Map<Long, Integer> rawMaterialsAndQuantity,

    @NotNull(message = "Order date cannot be null")
    LocalDate orderDate
){}
