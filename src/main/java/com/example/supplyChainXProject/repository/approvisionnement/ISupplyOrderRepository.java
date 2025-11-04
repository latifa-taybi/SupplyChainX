package com.example.supplyChainXProject.repository.approvisionnement;

import com.example.supplyChainXProject.entity.approvisionnement.SupplyOrder;
import com.example.supplyChainXProject.enums.SupplyOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {
    List<SupplyOrder> findSupplyOrderByStatus(SupplyOrderStatus status);
}
