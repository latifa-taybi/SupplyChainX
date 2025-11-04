package com.example.supplyChainXProject.repository.approvisionnement;

import com.example.supplyChainXProject.entity.approvisionnement.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRawMaterialRepository extends JpaRepository<RawMaterial, Long> {
    List<RawMaterial> findRawMaterialByStockLessThanEqual (Integer stockCritique);
}
