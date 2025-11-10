package com.example.supplyChainXProject.dto.livraison.response;

import com.example.supplyChainXProject.dto.livraison.response.order.OrderResponseDto;
import com.example.supplyChainXProject.enums.DeliveryStatus;


import java.time.LocalDateTime ;

public record DeliveryDtoResponse(
        OrderResponseDto order,

        String vehicle,
        String driver,
        DeliveryStatus status,
        LocalDateTime  deliveryDate,
        Double cost
){}
