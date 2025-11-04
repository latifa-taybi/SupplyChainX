package com.example.supplyChainXProject.entity.production;

import com.example.supplyChainXProject.entity.approvisionnement.RawMaterialSupplyOrder;
import com.example.supplyChainXProject.entity.livraison.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer productionTime;
    private Double cost;
    private Integer stock;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductionOrder> productionOrderList = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<BillOfMaterial> billOfMaterials = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders;

    public void addBillOfMaterial(BillOfMaterial item) {
        this.billOfMaterials.add(item);
        item.setProduct(this);
    }
}