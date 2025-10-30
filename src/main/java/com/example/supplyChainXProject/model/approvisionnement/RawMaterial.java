package com.example.supplyChainXProject.model.approvisionnement;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "raw_materials")
@Getter @Setter
public class RawMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaterial;

    @Column(nullable = false)
    private String name;

    private Integer stock;
    private Integer stockMin;
    private String unit;

    @ManyToMany
    @JoinTable(name = "material_suppliers", joinColumns = @JoinColumn(name = "material_id"), inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    private List<Supplier> suppliers;

    @Version
    private Long version;
}
