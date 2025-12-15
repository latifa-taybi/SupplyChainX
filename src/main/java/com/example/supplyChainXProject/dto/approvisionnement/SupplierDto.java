package com.example.supplyChainXProject.dto.approvisionnement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDto {

    @NotBlank(message = "le prenom du fournisseur est obligatoire")
    private String firstName;

    @NotEmpty(message = "le nom du fournisseur est obligatoire")
    private String lastName;

    private String contact;

    @NotNull(message = "la note du fournisseur est obligatoire")
    private Double rating;

    @NotNull(message = "le delai moyen est obligatoire")
    private Integer leadTime;


}