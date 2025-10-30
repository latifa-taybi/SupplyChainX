package com.example.supplyChainXProject.model.production;

import com.example.supplyChainXProject.model.approvisionnement.RawMaterial;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "boms")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BillOfMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBOM;

    @ManyToOne(optional = false)
    private Product product;

    @ManyToOne(optional = false)
    private RawMaterial material;

    @Column(nullable = false)
    private Integer quantity;
}
