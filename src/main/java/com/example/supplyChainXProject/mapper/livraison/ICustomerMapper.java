package com.example.supplyChainXProject.mapper.livraison;

import com.example.supplyChainXProject.dto.livraison.CustomerDto;
import com.example.supplyChainXProject.dto.livraison.response.customer.CustomerResponseDto;
import com.example.supplyChainXProject.dto.livraison.response.order.CustomerResponseDtoWithoutOrders;
import com.example.supplyChainXProject.entity.livraison.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {
    CustomerResponseDtoWithoutOrders toWithoutOrders(Customer customer);
    Customer toEntity(CustomerDto customerDto);
    CustomerResponseDto toDto(Customer customer);
}
