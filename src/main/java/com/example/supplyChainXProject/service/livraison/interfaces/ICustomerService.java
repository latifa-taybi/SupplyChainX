package com.example.supplyChainXProject.service.livraison.interfaces;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.SupplierDto;
import com.example.supplyChainXProject.dto.livraison.CustomerDto;
import com.example.supplyChainXProject.dto.livraison.response.customer.CustomerResponseDto;
import com.example.supplyChainXProject.entity.livraison.Customer;

import java.util.List;

public interface ICustomerService {
    CustomerResponseDto createCustomerr(CustomerDto customerDto);
    List<CustomerResponseDto> getAllCustomers();
    CustomerResponseDto updateCustomer(CustomerDto customerDto, Long id);
    CustomerResponseDto getCustomerById(Long id);
    MessageResponse deleteCustomer(Long id);
    CustomerResponseDto searchByName(String name);
}
