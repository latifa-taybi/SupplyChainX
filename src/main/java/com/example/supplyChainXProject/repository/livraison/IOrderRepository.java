package com.example.supplyChainXProject.repository.livraison;

import com.example.supplyChainXProject.entity.livraison.Order;
import com.example.supplyChainXProject.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository  extends JpaRepository<Order, Long> {
    List<Order> findOrderByStatus(OrderStatus status);
}
