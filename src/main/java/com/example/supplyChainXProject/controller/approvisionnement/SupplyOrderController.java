package com.example.supplyChainXProject.controller.approvisionnement;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.SupplierDto;
import com.example.supplyChainXProject.dto.approvisionnement.SupplyOrderDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierOrder.SupplyOrderDtoResponse;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse.SupplierDtoResponse;
import com.example.supplyChainXProject.enums.SupplyOrderStatus;
import com.example.supplyChainXProject.service.approvisionnement.interfaces.ISupplyOrderService;
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
@Tag(name = "supply order crud", description = "crud")
@RequestMapping("/api/supplyOrders")
public class SupplyOrderController {
    private final ISupplyOrderService supplyOrderService;

    @PostMapping
    public ResponseEntity<?> createSupplyOrder(@Valid @RequestBody SupplyOrderDto supplyOrderDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        return ResponseEntity.ok(supplyOrderService.createSupplyOrder(supplyOrderDto));
    }

    @GetMapping
    public ResponseEntity<List<SupplyOrderDtoResponse>> getAllSuppliers(){
        List<SupplyOrderDtoResponse> supplyOrdesrs = supplyOrderService.getAllSupplyOrders();
        return ResponseEntity.ok(supplyOrdesrs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable("id") Long id){
        return ResponseEntity.ok(supplyOrderService.getSupplyOrderById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplyOrder(@PathVariable("id") Long id, @Valid @RequestBody SupplyOrderDto supplyOrderDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        SupplyOrderDtoResponse supplyOrderDto1 = supplyOrderService.updateSupplyOrder(supplyOrderDto, id);
        return ResponseEntity.ok(supplyOrderDto1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteSupplyOrder(@PathVariable("id") Long id){
        MessageResponse messageResponse = supplyOrderService.deleteSupplyOrder(id);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<SupplyOrderDtoResponse>> getSupplyOrdersByStatus(@PathVariable("status") SupplyOrderStatus status) {
        List<SupplyOrderDtoResponse> orders = supplyOrderService.getSupplyOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }


}
