package com.example.supplyChainXProject.repository.production;

import com.example.supplyChainXProject.entity.production.ProductionOrder;
import com.example.supplyChainXProject.enums.ProductionOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductionOrderRepository extends JpaRepository<ProductionOrder, Long> {
    List<ProductionOrder> findProductionOrderByProductionOrderStatus(ProductionOrderStatus status);
}
