package com.example.supplyChainXProject.dto.approvisionnement;

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
public class RawMaterialDto {
    @NotBlank(message = "le nom du matiere premier est obligatoire")
    private String name;

    @NotNull(message = "le nombre du stock est obligatoire")
    private Integer stock;

    @NotNull(message = "le prenom du fournisseur est obligatoire")
    private Integer stockMin;

    @NotNull(message = "le prenom du fournisseur est obligatoire")
    private String unit;
    private List<Long> supplierIds;;

}
