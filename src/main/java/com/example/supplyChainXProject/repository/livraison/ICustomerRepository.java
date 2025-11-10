package com.example.supplyChainXProject.repository.livraison;

import com.example.supplyChainXProject.entity.livraison.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByName(String name);
}
