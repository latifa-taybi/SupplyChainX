package com.example.supplyChainXProject.service.approvisionnement.interfaces;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.SupplierDto;
import com.example.supplyChainXProject.dto.approvisionnement.SupplyOrderDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierOrder.SupplyOrderDtoResponse;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse.SupplierDtoResponse;
import com.example.supplyChainXProject.entity.approvisionnement.Supplier;
import com.example.supplyChainXProject.entity.approvisionnement.SupplyOrder;
import com.example.supplyChainXProject.enums.SupplyOrderStatus;

import java.util.List;

public interface ISupplyOrderService {
    SupplyOrderDtoResponse createSupplyOrder(SupplyOrderDto supplyOrderDto);
    List<SupplyOrderDtoResponse> getAllSupplyOrders();
    SupplyOrderDtoResponse updateSupplyOrder(SupplyOrderDto supplyOrderDto, Long id);
    SupplyOrderDtoResponse getSupplyOrderById(Long id);
    MessageResponse deleteSupplyOrder(Long id);
    List<SupplyOrderDtoResponse> getSupplyOrdersByStatus(SupplyOrderStatus status);
}
