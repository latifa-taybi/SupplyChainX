package com.example.supplyChainXProject.controller.livraison;
import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse.SupplierDtoResponse;
import com.example.supplyChainXProject.dto.livraison.CustomerDto;
import com.example.supplyChainXProject.dto.livraison.response.customer.CustomerResponseDto;
import com.example.supplyChainXProject.service.livraison.interfaces.ICustomerService;
import com.example.supplyChainXProject.utils.Validation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
@Tag(name = "custommer crud", description = "crud")
public class CustomerController {
    private final ICustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        return ResponseEntity.ok(customerService.createCustomerr(customerDto));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers(){
        List<CustomerResponseDto> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, @Valid @RequestBody CustomerDto customerDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        CustomerResponseDto customer = customerService.updateCustomer(customerDto, id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteCustomer(@PathVariable("id") Long id){
        MessageResponse messageResponse = customerService.deleteCustomer(id);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchCustomerByName(@PathVariable("name") String name) {
        CustomerResponseDto customer = customerService.searchByName(name);
        return ResponseEntity.ok(customer);
    }
}
