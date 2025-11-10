package com.example.supplyChainXProject.controller.livraison;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierOrder.SupplyOrderDtoResponse;
import com.example.supplyChainXProject.dto.livraison.OrderDto;
import com.example.supplyChainXProject.dto.livraison.response.order.OrderResponseDto;
import com.example.supplyChainXProject.enums.OrderStatus;
import com.example.supplyChainXProject.enums.SupplyOrderStatus;
import com.example.supplyChainXProject.service.livraison.interfaces.IOrderService;
import com.example.supplyChainXProject.utils.Validation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
@Tag(name = "order crud", description = "crud")
public class OrderController {
    private final IOrderService orderService;
    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") Long id, @Valid @RequestBody OrderDto orderDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        OrderResponseDto order = orderService.updateOrder(orderDto, id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(){
        List<OrderResponseDto> orders = orderService.getAllOrderss();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByStatus(@PathVariable("status") OrderStatus status) {
        List<OrderResponseDto> orders = orderService.getOrderByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteOrder(@PathVariable("id") Long id){
        MessageResponse messageResponse = orderService.deleteOrder(id);
        return ResponseEntity.ok(messageResponse);
    }
}
