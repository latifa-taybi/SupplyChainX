package com.example.supplyChainXProject.model.production;


import com.example.supplyChainXProject.enums.ProductionOrderStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "production_orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductionOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @ManyToOne(optional = false)
    private Product product;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private ProductionOrderStatus status;

    private LocalDate startDate;
    private LocalDate endDate;

    @Version
    private Long version;
}
