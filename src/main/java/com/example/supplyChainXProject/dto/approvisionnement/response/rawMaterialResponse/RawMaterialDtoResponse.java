package com.example.supplyChainXProject.dto.approvisionnement.response.rawMaterialResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterialDtoResponse {
    private String name;

    private Integer stock;

    private Integer stockMin;
    private String unit;
    private List<SupplierDtoResponseSansList> suppliers;;

}
