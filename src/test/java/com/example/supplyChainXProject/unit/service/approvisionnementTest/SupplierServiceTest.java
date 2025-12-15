package com.example.supplyChainXProject.unit.service.approvisionnementTest;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.SupplierDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse.SupplierDtoResponse;
import com.example.supplyChainXProject.entity.approvisionnement.Supplier;
import com.example.supplyChainXProject.entity.approvisionnement.SupplyOrder;
import com.example.supplyChainXProject.enums.SupplyOrderStatus;
import com.example.supplyChainXProject.mapper.approvisionnement.SupplierMapper;
import com.example.supplyChainXProject.repository.approvisionnement.ISupplierRepository;
import com.example.supplyChainXProject.service.approvisionnement.SupplierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplierServiceTest {

    @Mock
    private ISupplierRepository supplierRepository;

    @Mock
    private SupplierMapper supplierMapper;

    @InjectMocks
    private SupplierService supplierService;

    @Test
    void shouldCreateSupplier() {
        SupplierDto dto = new SupplierDto();
        Supplier supplier = new Supplier();

        when(supplierMapper.toEntity(dto)).thenReturn(supplier);
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        Supplier result = supplierService.createSupplier(dto);

        assertNotNull(result);
        verify(supplierMapper).toEntity(dto);
        verify(supplierRepository).save(supplier);
    }


    @Test
    void shouldGetAllSuppliers() {
        List<Supplier> suppliers = List.of(new Supplier(), new Supplier());

        when(supplierRepository.findAll()).thenReturn(suppliers);
        when(supplierMapper.toDtoResponse(any())).thenReturn(new SupplierDtoResponse());

        List<SupplierDtoResponse> result = supplierService.getAllSuppliers();

        assertEquals(2, result.size());
        verify(supplierRepository).findAll();
        verify(supplierMapper, times(2)).toDtoResponse(any());
    }



    @Test
    void shouldUpdateSupplier() {
        Long id = 1L;
        SupplierDto dto = new SupplierDto();
        Supplier supplier = new Supplier();

        when(supplierRepository.findById(id)).thenReturn(Optional.of(supplier));
        when(supplierRepository.save(supplier)).thenReturn(supplier);
        when(supplierMapper.toDTO(supplier)).thenReturn(dto);

        SupplierDto result = supplierService.updateSupplier(dto, id);

        assertNotNull(result);
        verify(supplierRepository).findById(id);
        verify(supplierRepository).save(any());
        verify(supplierMapper).toDTO(any());
    }



    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingSupplier() {
        Long id = 99L;
        SupplierDto dto = new SupplierDto();

        when(supplierRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> supplierService.updateSupplier(dto, id));
        verify(supplierRepository).findById(id);
    }


    @Test
    void shouldGetSupplierById() {
        Long id = 1L;
        Supplier supplier = new Supplier();
        SupplierDtoResponse response = new SupplierDtoResponse();

        when(supplierRepository.findById(id)).thenReturn(Optional.of(supplier));
        when(supplierMapper.toDtoResponse(supplier)).thenReturn(response);

        SupplierDtoResponse result = supplierService.getSupplierById(id);

        assertNotNull(result);
        verify(supplierRepository).findById(id);
        verify(supplierMapper).toDtoResponse(supplier);
    }


    @Test
    void shouldThrowExceptionWhenSupplierByIdNotFound() {
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> supplierService.getSupplierById(1L));
    }


    @Test
    void shouldDeleteSupplier() {
        Long id = 1L;

        Supplier supplier = new Supplier();
        supplier.setOrders(new ArrayList<>());

        when(supplierRepository.findById(id)).thenReturn(Optional.of(supplier));

        MessageResponse response = supplierService.deleteSupplier(id);

        assertEquals("supplier deleted successfuly", response.getMessage());
        verify(supplierRepository).delete(supplier);
    }

    @Test
    void shouldNotDeleteSupplierWhenActiveOrdersExist() {
        Long id = 1L;

        SupplyOrder activeOrder = new SupplyOrder();
        activeOrder.setStatus(SupplyOrderStatus.EN_COURS);

        Supplier supplier = new Supplier();
        supplier.setOrders(List.of(activeOrder));

        when(supplierRepository.findById(id)).thenReturn(Optional.of(supplier));

        MessageResponse response = supplierService.deleteSupplier(id);

        assertEquals("you can't delete this order because it is active", response.getMessage());
        verify(supplierRepository, never()).delete(any());
    }


    @Test
    void shouldReturnSupplierByName() {
        Supplier supplier = new Supplier();
        SupplierDtoResponse dto = new SupplierDtoResponse();

        when(supplierRepository.findByFirstNameAndLastName("John", "Doe")).thenReturn(supplier);
        when(supplierMapper.toDtoResponse(supplier)).thenReturn(dto);

        SupplierDtoResponse result = supplierService.searchByName("John", "Doe");

        assertNotNull(result);
        verify(supplierRepository).findByFirstNameAndLastName("John", "Doe");
    }

    @Test
    void shouldThrowExceptionWhenSupplierNotFoundByName() {
        when(supplierRepository.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> supplierService.searchByName("John", "Doe"));
    }
}
