package com.example.supplyChainXProject.service.production.interfaces;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.production.ProductionOrderDto;
import com.example.supplyChainXProject.dto.production.response.ProductionOrderDtoResponse;
import com.example.supplyChainXProject.enums.ProductionOrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface IProductionOrderService {
    ProductionOrderDtoResponse createProductionOrder(ProductionOrderDto productionOrderDto);
    List<ProductionOrderDtoResponse> getAllProductionOrders();
    ProductionOrderDtoResponse updateProductionOrder(ProductionOrderDto productionOrderDto, Long id);
    ProductionOrderDtoResponse getProductionOrderById(Long id);
    MessageResponse bloqueOrder(Long id);
    List<ProductionOrderDtoResponse> getProductionOrdersByStatus(ProductionOrderStatus status);
    long tempsEstime(Long id);
}
