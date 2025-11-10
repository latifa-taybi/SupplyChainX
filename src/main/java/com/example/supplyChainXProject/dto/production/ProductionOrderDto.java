package com.example.supplyChainXProject.dto.production;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime ;

public record ProductionOrderDto(
        @NotNull(message = "the quantity is required")
        Integer quantity,
        Long productId,
        @NotNull(message = "the start date is required")
        LocalDateTime  startDate
){}
