package com.example.supplyChainXProject.model.livraison;


import com.example.supplyChainXProject.enums.OrderStatus;
import com.example.supplyChainXProject.model.production.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @ManyToOne(optional = false)
    private Customer customer;

    @ManyToOne(optional = false)
    private Product product;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
