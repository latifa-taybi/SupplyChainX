package com.example.supplyChainXProject.service.livraison;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.SupplierDto;
import com.example.supplyChainXProject.dto.livraison.CustomerDto;
import com.example.supplyChainXProject.dto.livraison.response.customer.CustomerResponseDto;
import com.example.supplyChainXProject.entity.approvisionnement.Supplier;
import com.example.supplyChainXProject.entity.livraison.Customer;
import com.example.supplyChainXProject.entity.livraison.Order;
import com.example.supplyChainXProject.enums.OrderStatus;
import com.example.supplyChainXProject.mapper.livraison.ICustomerMapper;
import com.example.supplyChainXProject.repository.livraison.ICustomerRepository;
import com.example.supplyChainXProject.service.livraison.interfaces.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {
    private final ICustomerRepository customerRepository;
    private final ICustomerMapper customerMapper;
    @Override
    public CustomerResponseDto createCustomerr(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::toDto).toList();

    }

    @Override
    public CustomerResponseDto updateCustomer(CustomerDto customerDto, Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->new RuntimeException("customer Not Found"));
        customer.setName(customerDto.name());
        customer.setCity(customerDto.city());
        customer.setAddress(customerDto.address());

        Customer svaingCustomer =  customerRepository.save(customer);
        return customerMapper.toDto(svaingCustomer);
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->new RuntimeException("customer Not Found"));
        return customerMapper.toDto(customer);
    }

    @Override
    public MessageResponse deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->new RuntimeException("customer Not Found"));
        for(Order order:customer.getOrders()){
            if(order.getStatus().equals(OrderStatus.EN_PREPARATION) || order.getStatus().equals(OrderStatus.EN_ROUTE)){
                return new MessageResponse("you can not delete this order");
            }
        }
            customerRepository.delete(customer);
            return new MessageResponse("customer deleted succefully");
    }

    @Override
    public CustomerResponseDto searchByName(String name){
        Customer customer = customerRepository.findCustomerByName(name);
        if(customer == null){
            throw  new RuntimeException("customer not found");
        }
        return customerMapper.toDto(customer);
    }
}
