package com.example.supplyChainXProject.service.livraison.interfaces;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.livraison.CustomerDto;
import com.example.supplyChainXProject.dto.livraison.OrderDto;
import com.example.supplyChainXProject.dto.livraison.response.customer.CustomerResponseDto;
import com.example.supplyChainXProject.dto.livraison.response.order.OrderResponseDto;
import com.example.supplyChainXProject.enums.OrderStatus;

import java.util.List;

public interface IOrderService {
    OrderResponseDto createOrder(OrderDto orderDto);
    List<OrderResponseDto> getAllOrderss();
    OrderResponseDto updateOrder(OrderDto orderDto, Long id);
    OrderResponseDto getOrderById(Long id);
    MessageResponse deleteOrder(Long id);
    List<OrderResponseDto> getOrderByStatus(OrderStatus status);
}
