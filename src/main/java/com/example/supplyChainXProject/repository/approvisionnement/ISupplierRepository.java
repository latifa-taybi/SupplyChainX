package com.example.supplyChainXProject.repository.approvisionnement;

import com.example.supplyChainXProject.entity.approvisionnement.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ISupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findByFirstNameAndLastName(String firstName,String lastName);
}
