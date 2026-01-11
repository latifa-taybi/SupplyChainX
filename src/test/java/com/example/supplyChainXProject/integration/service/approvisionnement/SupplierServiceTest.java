package com.example.supplyChainXProject.integration.service.approvisionnement;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.SupplierDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse.SupplierDtoResponse;
import com.example.supplyChainXProject.entity.approvisionnement.Supplier;
import com.example.supplyChainXProject.entity.approvisionnement.SupplyOrder;
import com.example.supplyChainXProject.enums.SupplyOrderStatus;
import com.example.supplyChainXProject.integration.AbstractIntegrationTest;
import com.example.supplyChainXProject.repository.approvisionnement.ISupplierRepository;
import com.example.supplyChainXProject.service.approvisionnement.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//@Testcontainers
//@ActiveProfiles("test")
//public class SupplierServiceTest extends AbstractIntegrationTest {
//    @Autowired
//    private SupplierService supplierService;
//
//    @Autowired
//    private ISupplierRepository supplierRepository;
//
//    @BeforeEach
//    void setUp() {
//        supplierRepository.deleteAll();
//    }
//
//    @Test
//    void shouldCreateSupplier() {
//        SupplierDto dto = new SupplierDto();
//        dto.setFirstName("Samsung");
//
//        Supplier supplier = supplierService.createSupplier(dto);
//
//        assertNotNull(supplier.getIdSupplier());
//        assertEquals("Samsung", supplier.getFirstName());
//    }
//
//    @Test
//    void shouldGetAllSuppliers() {
//        SupplierDto dto1 = new SupplierDto();
//        dto1.setFirstName("Samsung");
//        dto1.setLastName("Electronics");
//
//        SupplierDto dto2 = new SupplierDto();
//        dto2.setFirstName("LG");
//        dto2.setLastName("Electronics");
//
//        supplierService.createSupplier(dto1);
//        supplierService.createSupplier(dto2);
//
//        List<SupplierDtoResponse> suppliers = supplierService.getAllSuppliers();
//
//        assertEquals(2, suppliers.size());
//        assertTrue(suppliers.stream().anyMatch(s -> s.getFirstName().equals("Samsung") && s.getLastName().equals("Electronics")));
//        assertTrue(suppliers.stream().anyMatch(s -> s.getFirstName().equals("LG") && s.getLastName().equals("Electronics")));
//    }
//
//
//    @Test
//    void shouldUpdateSupplier() {
//        SupplierDto dto = new SupplierDto();
//        dto.setFirstName("Samsung");
//        Supplier created = supplierService.createSupplier(dto);
//
//        SupplierDto updateDto = new SupplierDto();
//        updateDto.setFirstName("Sony");
//
//        SupplierDto updated = supplierService.updateSupplier(updateDto, created.getIdSupplier());
//        assertEquals("Sony", updated.getFirstName());
//    }
//
//    @Test
//    void shouldGetSupplierById() {
//        SupplierDto dto = new SupplierDto();
//        dto.setFirstName("Samsung");
//        Supplier created = supplierService.createSupplier(dto);
//
//        SupplierDtoResponse response = supplierService.getSupplierById(created.getIdSupplier());
//        assertEquals("Samsung", response.getFirstName());
//    }
//
//    @Test
//    void shouldDeleteSupplier() {
//        SupplierDto dto = new SupplierDto();
//        dto.setFirstName("Samsung");
//        Supplier created = supplierService.createSupplier(dto);
//
//        MessageResponse response = supplierService.deleteSupplier(created.getIdSupplier());
//        assertEquals("supplier deleted successfuly", response.getMessage());
//        assertTrue(supplierRepository.findById(created.getIdSupplier()).isEmpty());
//    }
//
//    @Test
//    void shouldNotDeleteSupplierWithActiveOrders() {
//        SupplierDto dto = new SupplierDto();
//        dto.setFirstName("Samsung");
//        Supplier created = supplierService.createSupplier(dto);
//
//        SupplyOrder order = new SupplyOrder();
//        order.setStatus(SupplyOrderStatus.EN_COURS);
//        order.setSupplier(created);
//        created.getOrders().add(order);
//        supplierRepository.save(created);
//
//        MessageResponse response = supplierService.deleteSupplier(created.getIdSupplier());
//        assertEquals("you can't delete this order because it is active", response.getMessage());
//    }
//
//    @Test
//    void shouldSearchByName() {
//        SupplierDto dto = new SupplierDto();
//        dto.setFirstName("Samsung");
//        dto.setLastName("Electronics");
//        supplierService.createSupplier(dto);
//
//        SupplierDtoResponse response = supplierService.searchByName("Samsung", "Electronics");
//        assertEquals("Samsung", response.getFirstName());
//        assertEquals("Electronics", response.getLastName());
//    }
//}
