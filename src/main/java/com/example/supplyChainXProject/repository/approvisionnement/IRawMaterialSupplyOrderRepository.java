package com.example.supplyChainXProject.repository.approvisionnement;

import com.example.supplyChainXProject.entity.approvisionnement.RawMaterialSupplyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRawMaterialSupplyOrderRepository extends JpaRepository<RawMaterialSupplyOrder, Long> {

}
