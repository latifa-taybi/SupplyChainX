package com.example.supplyChainXProject.entity.livraison;


import com.example.supplyChainXProject.enums.OrderStatus;
import com.example.supplyChainXProject.entity.production.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id_delivery")
    private Delivery delivery;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
