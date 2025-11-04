package com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterialDtoResponseSansList {
    private String name;
    private Integer stock;
    private Integer stockMin;
    private String unit;
}
