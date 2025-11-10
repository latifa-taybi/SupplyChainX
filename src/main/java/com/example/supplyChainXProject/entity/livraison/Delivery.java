package com.example.supplyChainXProject.entity.livraison;

import com.example.supplyChainXProject.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime ;

@Entity
@Table(name = "deliveries")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelivery;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id_order")
    private Order order;

    private String vehicle;
    private String driver;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private LocalDateTime  deliveryDate;
    private Double cost;
}
