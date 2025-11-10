package com.example.supplyChainXProject.dto.production.response;

import com.example.supplyChainXProject.enums.ProductionOrderStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime ;

public record ProductionOrderDtoResponse(
        Integer quantity,
        ProductionOrderStatus productionOrderStatus,
        ProductResponseDto product,
        LocalDateTime  startDate,
        LocalDateTime  endDate
){}
