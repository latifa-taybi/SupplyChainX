package com.example.supplyChainXProject.service.approvisionnement;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.SupplyOrderDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierOrder.SupplyOrderDtoResponse;
import com.example.supplyChainXProject.entity.approvisionnement.RawMaterial;
import com.example.supplyChainXProject.entity.approvisionnement.RawMaterialSupplyOrder;
import com.example.supplyChainXProject.entity.approvisionnement.Supplier;
import com.example.supplyChainXProject.entity.approvisionnement.SupplyOrder;
import com.example.supplyChainXProject.enums.SupplyOrderStatus;
import com.example.supplyChainXProject.mapper.approvisionnement.ISupplyOrderMapper;
import com.example.supplyChainXProject.repository.approvisionnement.IRawMaterialRepository;
import com.example.supplyChainXProject.repository.approvisionnement.IRawMaterialSupplyOrderRepository;
import com.example.supplyChainXProject.repository.approvisionnement.ISupplierRepository;
import com.example.supplyChainXProject.repository.approvisionnement.ISupplyOrderRepository;
import com.example.supplyChainXProject.service.approvisionnement.interfaces.ISupplyOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SupplyOrderService implements ISupplyOrderService {
    private final ISupplyOrderRepository supplyOrderRepository;
    private final ISupplyOrderMapper supplyOrderMapper;
    private final ISupplierRepository supplierRepository;
    private final IRawMaterialRepository rawMaterialRepository;

    @Override
    public SupplyOrderDtoResponse createSupplyOrder(SupplyOrderDto supplyOrderDto) {
        Supplier supplier = supplierRepository.findById(supplyOrderDto.supplierId()).orElseThrow(() -> new RuntimeException("Supplier not found"));
        SupplyOrder order = new SupplyOrder();

        order.setSupplier(supplier);
        order.setOrderDate(supplyOrderDto.orderDate());
        order.setStatus(SupplyOrderStatus.EN_ATTENTE);

        for (Map.Entry<Long, Integer> entry : supplyOrderDto.rawMaterialsAndQuantity().entrySet()) {
            Long materialId = entry.getKey();
            Integer quantity = entry.getValue();
            RawMaterial material = rawMaterialRepository.findById(materialId).orElseThrow(() -> new RuntimeException("Raw Material not found"));
            RawMaterialSupplyOrder rawMaterialSupplyOrder = new RawMaterialSupplyOrder();
            rawMaterialSupplyOrder.setRawMaterial(material);
            rawMaterialSupplyOrder.setQuantity(quantity);
            order.addRawMaterialSupplyOrder(rawMaterialSupplyOrder);
        }
        SupplyOrder supplyOrder =  supplyOrderRepository.save(order);
        return supplyOrderMapper.toDtoResponse(supplyOrder);
    }

    @Override
    public List<SupplyOrderDtoResponse> getAllSupplyOrders() {
        List<SupplyOrder> supplyOrders = supplyOrderRepository.findAll();
        return supplyOrders.stream().map(supplyOrderMapper::toDtoResponse).toList();
    }

    @Override
    public SupplyOrderDtoResponse updateSupplyOrder(SupplyOrderDto supplyOrderDto, Long id) {
        SupplyOrder order = supplyOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("supply order not found"));

        if(order.getStatus().equals(SupplyOrderStatus.RECUE)) {
            throw new RuntimeException("you cannot update this order because is been received");
        }
        Supplier supplier = supplierRepository.findById(supplyOrderDto.supplierId()).orElseThrow(()->new RuntimeException("supplier not found"));

        order.setSupplier(supplier);
        order.setOrderDate(supplyOrderDto.orderDate());
        order.getRawMaterialSupplyOrders().clear();
        for (Map.Entry<Long, Integer> entry : supplyOrderDto.rawMaterialsAndQuantity().entrySet()) {
            Long materialId = entry.getKey();
            Integer quantity = entry.getValue();
            RawMaterial material = rawMaterialRepository.findById(materialId).orElseThrow(() -> new RuntimeException("raw material not found"));

            RawMaterialSupplyOrder rawMaterialSupplyOrder = new RawMaterialSupplyOrder();
            rawMaterialSupplyOrder.setRawMaterial(material);
            rawMaterialSupplyOrder.setQuantity(quantity);
            rawMaterialSupplyOrder.setSupplyOrder(order);
            order.addRawMaterialSupplyOrder(rawMaterialSupplyOrder);
        }
        SupplyOrder updatedOrder = supplyOrderRepository.save(order);

        return supplyOrderMapper.toDtoResponse(updatedOrder);
    }

    @Override
    public SupplyOrderDtoResponse getSupplyOrderById(Long id) {
            SupplyOrder supplyOrder = supplyOrderRepository.findById(id).orElseThrow(()->new RuntimeException("order Not Found"));
            SupplyOrderDtoResponse supplyOrderDtoResponse = supplyOrderMapper.toDtoResponse(supplyOrder);
            return supplyOrderDtoResponse;
    }

    @Override
    public MessageResponse deleteSupplyOrder(Long id) {
        SupplyOrder supplyOrder = supplyOrderRepository.findById(id).orElseThrow(()->new RuntimeException("order Not Found"));
        if(supplyOrder.getStatus().equals(SupplyOrderStatus.RECUE)){
            return new MessageResponse("you can't delete this order");
        }
        supplyOrderRepository.delete(supplyOrder);
        return new MessageResponse("supplier deleted successfuly");
    }

    @Override
    public List<SupplyOrderDtoResponse> getSupplyOrdersByStatus(SupplyOrderStatus status) {
        List<SupplyOrder> orders = supplyOrderRepository.findSupplyOrderByStatus(status);
        return orders.stream().map(supplyOrderMapper::toDtoResponse).toList();
    }


}
