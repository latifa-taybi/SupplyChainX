package com.example.supplyChainXProject.dto.livraison.response.order;

import com.example.supplyChainXProject.dto.production.response.ProductResponseDto;

public record OrderResponseDto(
        CustomerResponseDtoWithoutOrders customer,
        ProductResponseDto product,
        Integer quantity
){}
