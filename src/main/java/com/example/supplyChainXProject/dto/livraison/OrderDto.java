package com.example.supplyChainXProject.dto.livraison;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record OrderDto(
        @NotNull(message = "the customer is required")
        Long customerId,
        @NotNull(message = "the product is required")
        Long productId,
        @NotNull(message = "the quantity is required")
        Integer quantity
){}
