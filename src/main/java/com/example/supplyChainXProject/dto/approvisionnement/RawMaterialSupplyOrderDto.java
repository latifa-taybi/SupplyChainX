package com.example.supplyChainXProject.dto.approvisionnement;

import com.example.supplyChainXProject.entity.approvisionnement.RawMaterial;
import com.example.supplyChainXProject.entity.approvisionnement.SupplyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class RawMaterialSupplyOrderDto {
    private Integer quantity;

    private RawMaterial rawMaterial;

    private SupplyOrder supplyOrder;
}
