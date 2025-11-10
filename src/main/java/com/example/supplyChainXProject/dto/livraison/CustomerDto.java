package com.example.supplyChainXProject.dto.livraison;

import jakarta.validation.constraints.NotEmpty;

public record CustomerDto(
        @NotEmpty(message = "the name is required")
        String name,
        @NotEmpty(message = "the address is required")
        String address,
        @NotEmpty(message = "the city is required")
        String city
){}
