package com.example.supplyChainXProject.unit.service.approvisionnementTest;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.SupplyOrderDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.rawMaterialResponse.SupplierDtoResponseSansList;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierOrder.SupplyOrderDtoResponse;
import com.example.supplyChainXProject.entity.approvisionnement.RawMaterial;
import com.example.supplyChainXProject.entity.approvisionnement.Supplier;
import com.example.supplyChainXProject.entity.approvisionnement.SupplyOrder;
import com.example.supplyChainXProject.enums.SupplyOrderStatus;
import com.example.supplyChainXProject.mapper.approvisionnement.ISupplyOrderMapper;
import com.example.supplyChainXProject.repository.approvisionnement.IRawMaterialRepository;
import com.example.supplyChainXProject.repository.approvisionnement.ISupplierRepository;
import com.example.supplyChainXProject.repository.approvisionnement.ISupplyOrderRepository;
import com.example.supplyChainXProject.service.approvisionnement.SupplyOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplyOrderServiceTest {

    @Mock
    private ISupplyOrderRepository supplyOrderRepository;

    @Mock
    private ISupplyOrderMapper supplyOrderMapper;

    @Mock
    private ISupplierRepository supplierRepository;

    @Mock
    private IRawMaterialRepository rawMaterialRepository;

    @InjectMocks
    private SupplyOrderService supplyOrderService;

    @Test
    void shouldCreateSupplyOrder() {
        Supplier supplier = new Supplier();
        RawMaterial material = new RawMaterial();
        material.setStock(100);
        SupplyOrderDto dto = new SupplyOrderDto(
                1L,
                Map.of(1L, 5),
                LocalDateTime.now()
        );

        SupplyOrder savedOrder = new SupplyOrder();
        SupplyOrderDtoResponse response = new SupplyOrderDtoResponse(
                1L, new SupplierDtoResponseSansList(), LocalDateTime.now(), SupplyOrderStatus.EN_ATTENTE, List.of()
        );

        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(rawMaterialRepository.findById(1L)).thenReturn(Optional.of(material));
        when(supplyOrderRepository.save(any(SupplyOrder.class))).thenReturn(savedOrder);
        when(supplyOrderMapper.toDtoResponse(any(SupplyOrder.class))).thenReturn(response);

        SupplyOrderDtoResponse result = supplyOrderService.createSupplyOrder(dto);

        assertNotNull(result);
        verify(supplierRepository).findById(1L);
        verify(rawMaterialRepository).findById(1L);
        verify(supplyOrderRepository).save(any(SupplyOrder.class));
        verify(supplyOrderMapper).toDtoResponse(savedOrder);
    }

    @Test
    void shouldGetAllSupplyOrders() {
        List<SupplyOrder> orders = List.of(new SupplyOrder(), new SupplyOrder());
        when(supplyOrderRepository.findAll()).thenReturn(orders);
        when(supplyOrderMapper.toDtoResponse(any(SupplyOrder.class))).thenReturn(
                new SupplyOrderDtoResponse(1L, new SupplierDtoResponseSansList(), LocalDateTime.now(), SupplyOrderStatus.EN_ATTENTE, List.of())
        );

        List<SupplyOrderDtoResponse> result = supplyOrderService.getAllSupplyOrders();

        assertEquals(2, result.size());
        verify(supplyOrderRepository).findAll();
        verify(supplyOrderMapper, times(2)).toDtoResponse(any(SupplyOrder.class));
    }

    @Test
    void shouldUpdateSupplyOrder() {
        Long id = 1L;
        Supplier supplier = new Supplier();
        RawMaterial material = new RawMaterial();
        SupplyOrder order = new SupplyOrder();
        order.setStatus(SupplyOrderStatus.EN_ATTENTE);
        SupplyOrderDto dto = new SupplyOrderDto(
                1L,
                Map.of(1L, 10),
                LocalDateTime.now()
        );
        SupplyOrder savedOrder = new SupplyOrder();
        SupplyOrderDtoResponse response = new SupplyOrderDtoResponse(
                1L, new SupplierDtoResponseSansList(), LocalDateTime.now(), SupplyOrderStatus.EN_ATTENTE, List.of()
        );

        when(supplyOrderRepository.findById(id)).thenReturn(Optional.of(order));
        when(supplierRepository.findById(dto.supplierId())).thenReturn(Optional.of(supplier));
        when(rawMaterialRepository.findById(1L)).thenReturn(Optional.of(material));
        when(supplyOrderRepository.save(order)).thenReturn(savedOrder);
        when(supplyOrderMapper.toDtoResponse(savedOrder)).thenReturn(response);

        SupplyOrderDtoResponse result = supplyOrderService.updateSupplyOrder(dto, id);

        assertNotNull(result);
        verify(supplyOrderRepository).findById(id);
        verify(supplyOrderRepository).save(order);
        verify(supplyOrderMapper).toDtoResponse(savedOrder);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingReceivedOrder() {
        Long id = 1L;
        SupplyOrder order = new SupplyOrder();
        order.setStatus(SupplyOrderStatus.RECUE);

        when(supplyOrderRepository.findById(id)).thenReturn(Optional.of(order));

        SupplyOrderDto dto = new SupplyOrderDto(1L, Map.of(), LocalDateTime.now());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> supplyOrderService.updateSupplyOrder(dto, id));
        assertEquals("you cannot update this order because is been received", ex.getMessage());
    }

    @Test
    void shouldGetSupplyOrderById() {
        Long id = 1L;
        SupplyOrder order = new SupplyOrder();
        SupplyOrderDtoResponse response = new SupplyOrderDtoResponse(
                1L, new SupplierDtoResponseSansList(), LocalDateTime.now(), SupplyOrderStatus.EN_ATTENTE, List.of()
        );

        when(supplyOrderRepository.findById(id)).thenReturn(Optional.of(order));
        when(supplyOrderMapper.toDtoResponse(order)).thenReturn(response);

        SupplyOrderDtoResponse result = supplyOrderService.getSupplyOrderById(id);

        assertNotNull(result);
        verify(supplyOrderRepository).findById(id);
        verify(supplyOrderMapper).toDtoResponse(order);
    }

    @Test
    void shouldThrowExceptionWhenSupplyOrderNotFound() {
        when(supplyOrderRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> supplyOrderService.getSupplyOrderById(1L));
    }

    @Test
    void shouldDeleteSupplyOrderSuccessfully() {
        Long id = 1L;
        SupplyOrder order = new SupplyOrder();
        order.setStatus(SupplyOrderStatus.EN_ATTENTE);

        when(supplyOrderRepository.findById(id)).thenReturn(Optional.of(order));

        MessageResponse response = supplyOrderService.deleteSupplyOrder(id);

        assertEquals("supplier deleted successfuly", response.getMessage());
        verify(supplyOrderRepository).delete(order);
    }

    @Test
    void shouldNotDeleteReceivedOrder() {
        Long id = 1L;
        SupplyOrder order = new SupplyOrder();
        order.setStatus(SupplyOrderStatus.RECUE);

        when(supplyOrderRepository.findById(id)).thenReturn(Optional.of(order));

        MessageResponse response = supplyOrderService.deleteSupplyOrder(id);

        assertEquals("you can't delete this order", response.getMessage());
        verify(supplyOrderRepository, never()).delete(any());
    }

    @Test
    void shouldGetSupplyOrdersByStatus() {
        List<SupplyOrder> orders = List.of(new SupplyOrder(), new SupplyOrder());
        when(supplyOrderRepository.findSupplyOrderByStatus(SupplyOrderStatus.EN_ATTENTE)).thenReturn(orders);
        when(supplyOrderMapper.toDtoResponse(any(SupplyOrder.class))).thenReturn(
                new SupplyOrderDtoResponse(1L, new SupplierDtoResponseSansList(), LocalDateTime.now(), SupplyOrderStatus.EN_ATTENTE, List.of())
        );

        List<SupplyOrderDtoResponse> result = supplyOrderService.getSupplyOrdersByStatus(SupplyOrderStatus.EN_ATTENTE);

        assertEquals(2, result.size());
        verify(supplyOrderRepository).findSupplyOrderByStatus(SupplyOrderStatus.EN_ATTENTE);
        verify(supplyOrderMapper, times(2)).toDtoResponse(any(SupplyOrder.class));
    }
}
