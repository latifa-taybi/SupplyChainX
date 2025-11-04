package com.example.supplyChainXProject.dto.approvisionnement.response.rawMaterialResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDtoResponseSansList {
    private String firstName;
    private String lastName;
    private String contact;
    private Double rating;
    private Integer leadTime;


}
