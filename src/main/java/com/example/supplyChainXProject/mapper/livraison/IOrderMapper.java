package com.example.supplyChainXProject.mapper.livraison;

import com.example.supplyChainXProject.dto.livraison.OrderDto;
import com.example.supplyChainXProject.dto.livraison.response.customer.OrderResponseDtoWithoutCustomer;
import com.example.supplyChainXProject.dto.livraison.response.order.OrderResponseDto;
import com.example.supplyChainXProject.entity.livraison.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IOrderMapper {
    OrderResponseDto toDtoResponse(Order order);
    Order toEntity(OrderDto orderDto);
    OrderResponseDtoWithoutCustomer toWithoutCustomer(Order order);
}
