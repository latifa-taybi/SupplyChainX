package com.example.supplyChainXProject.service.livraison;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierOrder.SupplyOrderDtoResponse;
import com.example.supplyChainXProject.dto.livraison.OrderDto;
import com.example.supplyChainXProject.dto.livraison.response.order.OrderResponseDto;
import com.example.supplyChainXProject.entity.approvisionnement.SupplyOrder;
import com.example.supplyChainXProject.entity.livraison.Customer;
import com.example.supplyChainXProject.entity.livraison.Delivery;
import com.example.supplyChainXProject.entity.livraison.Order;
import com.example.supplyChainXProject.entity.production.Product;
import com.example.supplyChainXProject.enums.DeliveryStatus;
import com.example.supplyChainXProject.enums.OrderStatus;
import com.example.supplyChainXProject.enums.SupplyOrderStatus;
import com.example.supplyChainXProject.mapper.livraison.IOrderMapper;
import com.example.supplyChainXProject.repository.livraison.ICustomerRepository;
import com.example.supplyChainXProject.repository.livraison.IOrderRepository;
import com.example.supplyChainXProject.repository.production.IProductRepository;
import com.example.supplyChainXProject.repository.production.IProductionOrderRepository;
import com.example.supplyChainXProject.service.livraison.interfaces.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime ;
import java.util.List;
@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    private final IProductRepository productRepository;
    private final IOrderMapper orderMapper;
    private final ICustomerRepository customerRepository;
    private final IOrderRepository orderRepository;
    @Override
    public OrderResponseDto createOrder(OrderDto orderDto) {
        Product product = productRepository.findById(orderDto.productId()).orElseThrow(()->new RuntimeException("product not found"));
        Customer customer = customerRepository.findById(orderDto.customerId()).orElseThrow(()->new RuntimeException("customer not found"));
        if(product.getStock() < orderDto.quantity()){
            throw new RuntimeException("la quantite est insuffisante");
        }
        product.setStock(product.getStock() - orderDto.quantity());
        productRepository.save(product);
        Delivery delivery = new Delivery();
        delivery.setVehicle("vehicule");
        delivery.setDriver("driver");
        delivery.setCost(product.getCost() * orderDto.quantity());
        delivery.setDeliveryDate(LocalDateTime .now().plusWeeks(1));
        delivery.setStatus(DeliveryStatus.EN_COURS);
        Order order = orderMapper.toEntity(orderDto);
        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(orderDto.quantity());
        order.setStatus(OrderStatus.EN_PREPARATION);
        order.setDelivery(delivery);

        return orderMapper.toDtoResponse(orderRepository.save(order));
    }

    @Override
    public List<OrderResponseDto> getAllOrderss() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toDtoResponse).toList();
    }

    @Override
    public OrderResponseDto updateOrder(OrderDto orderDto, Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("order not found"));
        Product product = productRepository.findById(orderDto.productId()).orElseThrow(()->new RuntimeException("product not found"));
        Customer customer = customerRepository.findById(orderDto.customerId()).orElseThrow(()->new RuntimeException("customer not found"));
        Product oldProduct = productRepository.findById(order.getProduct().getId()).orElseThrow(()->new RuntimeException("product not found"));
        oldProduct.setStock(oldProduct.getStock() + order.getQuantity());
        productRepository.save(oldProduct);
        if(product.getStock() < orderDto.quantity()){
            throw new RuntimeException("la quantite est insuffisante");
        }
        product.setStock(product.getStock() - orderDto.quantity());
        productRepository.save(product);
        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(orderDto.quantity());
        order.getDelivery().setDeliveryDate(LocalDateTime .now().plusWeeks(1));
        order.getDelivery().setCost(product.getCost() * orderDto.quantity());
        return orderMapper.toDtoResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("order Not Found"));
        return orderMapper.toDtoResponse(order);
    }

    @Override
    public MessageResponse deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("order Not Found"));
        if(order.getStatus().equals(OrderStatus.LIVREE)){
            return new MessageResponse("you can't delete this order");
        }
        orderRepository.delete(order);
        return new MessageResponse("order deleted successfuly");
    }

    @Override
    public List<OrderResponseDto> getOrderByStatus(OrderStatus status){
        List<Order> orders = orderRepository.findOrderByStatus(status);
        return orders.stream().map(orderMapper::toDtoResponse).toList();
    }



}
