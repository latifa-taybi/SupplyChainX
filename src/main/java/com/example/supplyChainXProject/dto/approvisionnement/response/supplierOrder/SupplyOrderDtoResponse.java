package com.example.supplyChainXProject.dto.approvisionnement.response.supplierOrder;
import com.example.supplyChainXProject.dto.approvisionnement.response.rawMaterialResponse.SupplierDtoResponseSansList;
import com.example.supplyChainXProject.enums.SupplyOrderStatus;

import java.time.LocalDate;
import java.util.List;

public record SupplyOrderDtoResponse (
        Long idOrder,
        SupplierDtoResponseSansList supplier,
        LocalDate orderDate,
        SupplyOrderStatus status,
        List<RawMaterialSupplyOrderDtoResponse> rawMaterialSupplyOrders
){}
