package com.example.supplyChainXProject.repository.livraison;

import com.example.supplyChainXProject.entity.livraison.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeliveryRepository extends JpaRepository<Delivery, Long> {
}
