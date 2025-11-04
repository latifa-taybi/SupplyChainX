package com.example.supplyChainXProject.dto.production;
import com.example.supplyChainXProject.enums.ProductionOrderStatus;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

public record ProductionOrderDto(
        @NotNull(message = "the quantity is required")
        Integer Quantity,

        ProductionOrderStatus productionOrderStatus,
        Long productId,
        @NotNull(message = "the start date is required")
        LocalDate startDate,
        @NotNull(message = "the end date is required")
        LocalDate endDate
){}
