package com.example.supplyChainXProject.service.approvisionnement;


import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.SupplierDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse.SupplierDtoResponse;
import com.example.supplyChainXProject.entity.approvisionnement.Supplier;
import com.example.supplyChainXProject.entity.approvisionnement.SupplyOrder;
import com.example.supplyChainXProject.enums.SupplyOrderStatus;
import com.example.supplyChainXProject.mapper.approvisionnement.SupplierMapper;
import com.example.supplyChainXProject.repository.approvisionnement.ISupplierRepository;
import com.example.supplyChainXProject.service.approvisionnement.interfaces.ISupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@AllArgsConstructor
@Service
public class SupplierService implements ISupplierService {

    private ISupplierRepository supplierRepository;
    private SupplierMapper supplierMapper;

    @Override
    public Supplier createSupplier(SupplierDto supplierDto) {
        Supplier supplier = supplierMapper.toEntity(supplierDto);
        return supplierRepository.save(supplier);
    }

    @Override
    @Transactional
    public List<SupplierDtoResponse> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream().map(supplierMapper::toDtoResponse).toList();
    }

    @Override
    public SupplierDto updateSupplier(SupplierDto supplierDto, Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()->new RuntimeException("supplier Not Found"));
        supplier.setFirstName(supplierDto.getFirstName());
        supplier.setLastName(supplierDto.getLastName());
        supplier.setContact(supplierDto.getContact());
        supplier.setRating(supplierDto.getRating());
        supplier.setLeadTime(supplierDto.getLeadTime());

        Supplier svaingSupplier =  supplierRepository.save(supplier);
        return supplierMapper.toDTO(svaingSupplier);
    }

    @Override
    @Transactional
    public SupplierDtoResponse getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()->new RuntimeException("supplier Not Found"));
        return supplierMapper.toDtoResponse(supplier);
    }

    @Override
    @Transactional
    public MessageResponse deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()->new RuntimeException("supplier Not Found"));
        for (SupplyOrder order: supplier.getOrders()){
            if(order.getStatus().equals(SupplyOrderStatus.EN_ATTENTE) || order.getStatus().equals(SupplyOrderStatus.EN_COURS)){
                return new MessageResponse("you can't delete this order because it is active");
            }
        }
        supplierRepository.delete(supplier);
        return new MessageResponse("supplier deleted successfuly");
    }

    @Override
    @Transactional
    public SupplierDtoResponse searchByName(String firstName, String lastName){
        Supplier supplier = supplierRepository.findByFirstNameAndLastName(firstName, lastName);
        if(supplier == null){
          throw  new RuntimeException("supplier not found");
        }
        return supplierMapper.toDtoResponse(supplier);
    }
}
