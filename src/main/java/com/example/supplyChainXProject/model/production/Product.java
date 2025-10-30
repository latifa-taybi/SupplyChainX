package com.example.supplyChainXProject.model.production;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(nullable = false)
    private String name;

    private Integer productionTime;
    private Double cost;
    private Integer stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillOfMaterial> billOfMaterials;

    @Version
    private Long version;
}
