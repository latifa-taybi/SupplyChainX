package com.example.supplyChainXProject.service.approvisionnement.interfaces;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.SupplierDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse.SupplierDtoResponse;
import com.example.supplyChainXProject.entity.approvisionnement.Supplier;

import java.util.List;

public interface ISupplierService {
    Supplier createSupplier(SupplierDto supplierDto);
    List<SupplierDtoResponse> getAllSuppliers();
    SupplierDto updateSupplier(SupplierDto supplierDto, Long id);
    SupplierDtoResponse getSupplierById(Long id);
    MessageResponse deleteSupplier(Long id);
    SupplierDtoResponse searchByName(String firstname, String lastName);
}
