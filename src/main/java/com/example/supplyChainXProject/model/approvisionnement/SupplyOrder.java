package com.example.supplyChainXProject.model.approvisionnement;

import com.example.supplyChainXProject.enums.SupplyOrderStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "supply_orders")
@Getter @Setter
public class SupplyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @ManyToOne(optional = false)
    private Supplier supplier;

    @ManyToMany
    @JoinTable(name = "supply_order_materials",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    private List<RawMaterial> materials;

    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private SupplyOrderStatus status;

    @Version
    private Long version;
}
