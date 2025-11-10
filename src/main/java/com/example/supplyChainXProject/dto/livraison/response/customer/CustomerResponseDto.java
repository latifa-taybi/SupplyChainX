package com.example.supplyChainXProject.dto.livraison.response.customer;

import java.util.List;

public record CustomerResponseDto(
        String name,
        String address,
        String city,
        List<OrderResponseDtoWithoutCustomer> orders
){}
