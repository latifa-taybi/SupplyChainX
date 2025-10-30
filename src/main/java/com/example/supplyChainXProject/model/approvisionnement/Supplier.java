package com.example.supplyChainXProject.model.approvisionnement;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
@Getter @Setter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSupplier;

    @Column(nullable = false)
    private String name;

    private String contact;
    private Double rating;
    private Integer leadTime;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<SupplyOrder> orders;
}
