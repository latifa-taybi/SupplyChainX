package com.example.supplyChainXProject.dto.livraison.response.customer;

import com.example.supplyChainXProject.dto.livraison.response.order.CustomerResponseDtoWithoutOrders;
import com.example.supplyChainXProject.dto.production.response.ProductResponseDto;

public record OrderResponseDtoWithoutCustomer(
        CustomerResponseDtoWithoutOrders customer,
        ProductResponseDto product,
        Integer quantity
){}
