package com.example.supplyChainXProject.controller.production;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierOrder.SupplyOrderDtoResponse;
import com.example.supplyChainXProject.dto.production.ProductDto;
import com.example.supplyChainXProject.dto.production.ProductionOrderDto;
import com.example.supplyChainXProject.dto.production.response.ProductResponseDto;
import com.example.supplyChainXProject.dto.production.response.ProductionOrderDtoResponse;
import com.example.supplyChainXProject.enums.ProductionOrderStatus;
import com.example.supplyChainXProject.enums.SupplyOrderStatus;
import com.example.supplyChainXProject.service.production.interfaces.IProductionOrderService;
import com.example.supplyChainXProject.utils.Validation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "production order crud", description = "crud")
@RequestMapping("/api/productionOrder")
public class ProductionOrderController {
    private final IProductionOrderService productionOrderService;
    @PostMapping
    public ResponseEntity<?> createProductionOrder(@Valid @RequestBody ProductionOrderDto productionOrderDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        return ResponseEntity.ok(productionOrderService.createProductionOrder(productionOrderDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductionOrder(@PathVariable("id") Long id, @Valid @RequestBody ProductionOrderDto productionOrderDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        ProductionOrderDtoResponse productionOrderDtoResponse = productionOrderService.updateProductionOrder(productionOrderDto, id);
        return ResponseEntity.ok(productionOrderDtoResponse);
    }

    @PostMapping("/{id}")
    public ResponseEntity<MessageResponse> blockProductionOrder(@PathVariable("id") Long id){
        return ResponseEntity.ok(productionOrderService.bloqueOrder(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductionOrderDtoResponse>> getAllProductionOrders(){
        List<ProductionOrderDtoResponse> productionOrders = productionOrderService.getAllProductionOrders();
        return ResponseEntity.ok(productionOrders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductionOrderById(@PathVariable("id") Long id){
        return ResponseEntity.ok(productionOrderService.getProductionOrderById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProductionOrderDtoResponse>> getProductionOrdersByStatus(@PathVariable("status") ProductionOrderStatus status) {
        List<ProductionOrderDtoResponse> productionOrders = productionOrderService.getProductionOrdersByStatus(status);
        return ResponseEntity.ok(productionOrders);
    }

    @GetMapping("/periode/{id}")
    public ResponseEntity<?> getPeriodEstimate(@PathVariable("id") Long id){
        return ResponseEntity.ok("le temps estime est : " + productionOrderService.tempsEstime(id));
    }
}
