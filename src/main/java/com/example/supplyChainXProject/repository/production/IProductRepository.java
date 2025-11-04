package com.example.supplyChainXProject.repository.production;

import com.example.supplyChainXProject.entity.production.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
    Product findProductByName(String name);
}
