package com.example.supplyChainXProject.entity.production;


import com.example.supplyChainXProject.enums.ProductionOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime ;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductionOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private ProductionOrderStatus productionOrderStatus;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private LocalDateTime  startDate;
    private LocalDateTime  endDate;
}