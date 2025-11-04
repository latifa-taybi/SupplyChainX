package com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDtoResponse {
    private String firstName;
    private String lastName;
    private String contact;
    private Double rating;
    private Integer leadTime;
    private List<RawMaterialDtoResponseSansList> rawMaterials;
}
