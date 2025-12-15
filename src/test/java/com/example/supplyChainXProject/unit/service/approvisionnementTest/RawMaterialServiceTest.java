package com.example.supplyChainXProject.unit.service.approvisionnementTest;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.RawMaterialDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.rawMaterialResponse.RawMaterialDtoResponse;
import com.example.supplyChainXProject.entity.approvisionnement.RawMaterial;
import com.example.supplyChainXProject.entity.approvisionnement.RawMaterialSupplyOrder;
import com.example.supplyChainXProject.entity.approvisionnement.Supplier;
import com.example.supplyChainXProject.mapper.approvisionnement.IRawMatrialMapper;
import com.example.supplyChainXProject.repository.approvisionnement.IRawMaterialRepository;
import com.example.supplyChainXProject.repository.approvisionnement.IRawMaterialSupplyOrderRepository;
import com.example.supplyChainXProject.repository.approvisionnement.ISupplierRepository;
import com.example.supplyChainXProject.service.approvisionnement.RawMaterialService;
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
class RawMaterialServiceTest {

    @Mock
    private IRawMaterialRepository rawMaterialRepository;

    @Mock
    private IRawMatrialMapper rawMatrialMapper;

    @Mock
    private ISupplierRepository supplierRepository;

    @Mock
    private IRawMaterialSupplyOrderRepository rawMaterialSupplyOrderRepository;

    @InjectMocks
    private RawMaterialService rawMaterialService;

    @Test
    void shouldCreateRawMaterial() {
        RawMaterialDto dto = new RawMaterialDto();
        RawMaterial entity = new RawMaterial();
        RawMaterialDtoResponse response = new RawMaterialDtoResponse();
        List<Long> supplierIds = List.of(1L, 2L);
        dto.setSupplierIds(supplierIds);

        List<Supplier> suppliers = List.of(new Supplier(), new Supplier());

        when(rawMatrialMapper.toEntity(dto)).thenReturn(entity);
        when(supplierRepository.findAllById(supplierIds)).thenReturn(suppliers);
        when(rawMaterialRepository.save(entity)).thenReturn(entity);
        when(rawMatrialMapper.toDtoResponse(entity)).thenReturn(response);

        RawMaterialDtoResponse result = rawMaterialService.createRawMaterial(dto);

        assertNotNull(result);
        verify(rawMatrialMapper).toEntity(dto);
        verify(supplierRepository).findAllById(supplierIds);
        verify(rawMaterialRepository).save(entity);
        verify(rawMatrialMapper).toDtoResponse(entity);
    }

    @Test
    void shouldGetAllRawMaterials() {
        List<RawMaterial> rawMaterials = List.of(new RawMaterial(), new RawMaterial());
        when(rawMaterialRepository.findAll()).thenReturn(rawMaterials);
        when(rawMatrialMapper.toDtoResponse(any())).thenReturn(new RawMaterialDtoResponse());

        List<RawMaterialDtoResponse> result = rawMaterialService.getAllRawMaterials();

        assertEquals(2, result.size());
        verify(rawMaterialRepository).findAll();
        verify(rawMatrialMapper, times(2)).toDtoResponse(any());
    }

    @Test
    void shouldUpdateRawMaterial() {
        Long id = 1L;
        RawMaterialDto dto = new RawMaterialDto();
        dto.setSupplierIds(List.of(1L));
        RawMaterial entity = new RawMaterial();
        RawMaterialDtoResponse response = new RawMaterialDtoResponse();
        Supplier supplier = new Supplier();
        entity.setSuppliers(new ArrayList<>());

        when(rawMaterialRepository.findById(id)).thenReturn(Optional.of(entity));
        when(supplierRepository.findAllById(dto.getSupplierIds())).thenReturn(List.of(supplier));
        when(rawMaterialRepository.save(entity)).thenReturn(entity);
        when(rawMatrialMapper.toDtoResponse(entity)).thenReturn(response);

        RawMaterialDtoResponse result = rawMaterialService.updateRawMaterial(dto, id);

        assertNotNull(result);
        verify(rawMaterialRepository).findById(id);
        verify(rawMaterialRepository).save(entity);
        verify(rawMatrialMapper).toDtoResponse(entity);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingRawMaterial() {
        Long id = 99L;
        RawMaterialDto dto = new RawMaterialDto();

        when(rawMaterialRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> rawMaterialService.updateRawMaterial(dto, id));
        verify(rawMaterialRepository).findById(id);
    }

    @Test
    void shouldGetRawMaterialById() {
        Long id = 1L;
        RawMaterial entity = new RawMaterial();
        RawMaterialDtoResponse response = new RawMaterialDtoResponse();

        when(rawMaterialRepository.findById(id)).thenReturn(Optional.of(entity));
        when(rawMatrialMapper.toDtoResponse(entity)).thenReturn(response);

        RawMaterialDtoResponse result = rawMaterialService.getRawMaterialById(id);

        assertNotNull(result);
        verify(rawMaterialRepository).findById(id);
        verify(rawMatrialMapper).toDtoResponse(entity);
    }

    @Test
    void shouldThrowExceptionWhenRawMaterialByIdNotFound() {
        when(rawMaterialRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> rawMaterialService.getRawMaterialById(1L));
    }

    @Test
    void shouldDeleteRawMaterialSuccessfully() {
        Long id = 1L;
        RawMaterial entity = new RawMaterial();
        entity.setSuppliers(new ArrayList<>());
        when(rawMaterialRepository.findById(id)).thenReturn(Optional.of(entity));
        when(rawMaterialSupplyOrderRepository.findAll()).thenReturn(new ArrayList<>());

        MessageResponse response = rawMaterialService.deleteRawMaterial(id);

        assertEquals("The raw material was deleted successfully", response.getMessage());
        verify(rawMaterialRepository).delete(entity);
    }

    @Test
    void shouldNotDeleteRawMaterialWhenLinkedToSuppliers() {
        Long id = 1L;
        RawMaterial entity = new RawMaterial();
        entity.setSuppliers(List.of(new Supplier()));

        when(rawMaterialRepository.findById(id)).thenReturn(Optional.of(entity));

        MessageResponse response = rawMaterialService.deleteRawMaterial(id);

        assertEquals("tu ne peux pas supprimer cette matiere premier  car il est lier avec le fournisseur", response.getMessage());
        verify(rawMaterialRepository, never()).delete(any());
    }

    @Test
    void shouldNotDeleteRawMaterialWhenLinkedToOrders() {
        Long id = 1L;
        RawMaterial entity = new RawMaterial();
        entity.setSuppliers(new ArrayList<>());

        RawMaterialSupplyOrder order = new RawMaterialSupplyOrder();
        order.setRawMaterial(entity);

        when(rawMaterialRepository.findById(id)).thenReturn(Optional.of(entity));
        when(rawMaterialSupplyOrderRepository.findAll()).thenReturn(List.of(order));

        MessageResponse response = rawMaterialService.deleteRawMaterial(id);

        assertEquals("you can t delete this raw material", response.getMessage());
        verify(rawMaterialRepository, never()).delete(any());
    }

    @Test
    void shouldFilterByCritiqueStock() {
        RawMaterial entity1 = new RawMaterial();
        RawMaterial entity2 = new RawMaterial();
        List<RawMaterial> entities = List.of(entity1, entity2);

        when(rawMaterialRepository.findRawMaterialByStockLessThanEqual(10)).thenReturn(entities);
        when(rawMatrialMapper.toDtoResponse(any())).thenReturn(new RawMaterialDtoResponse());

        List<RawMaterialDtoResponse> result = rawMaterialService.filterByCritiqueStock(10);

        assertEquals(2, result.size());
        verify(rawMaterialRepository).findRawMaterialByStockLessThanEqual(10);
        verify(rawMatrialMapper, times(2)).toDtoResponse(any());
    }
}
