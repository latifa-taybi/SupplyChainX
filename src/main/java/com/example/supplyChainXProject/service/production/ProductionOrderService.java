package com.example.supplyChainXProject.service.production;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.production.ProductionOrderDto;
import com.example.supplyChainXProject.dto.production.response.BillOfMaterialResponseDto;
import com.example.supplyChainXProject.dto.production.response.ProductionOrderDtoResponse;
import com.example.supplyChainXProject.entity.approvisionnement.RawMaterial;
import com.example.supplyChainXProject.entity.production.BillOfMaterial;
import com.example.supplyChainXProject.entity.production.Product;
import com.example.supplyChainXProject.entity.production.ProductionOrder;
import com.example.supplyChainXProject.enums.ProductionOrderStatus;
import com.example.supplyChainXProject.mapper.production.IProductionOrderMapper;
import com.example.supplyChainXProject.repository.approvisionnement.IRawMaterialRepository;
import com.example.supplyChainXProject.repository.production.IProductRepository;
import com.example.supplyChainXProject.repository.production.IProductionOrderRepository;
import com.example.supplyChainXProject.service.production.interfaces.IProductionOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
@AllArgsConstructor
public class ProductionOrderService implements IProductionOrderService {
    private final IProductRepository productRepository;
    private final IProductionOrderMapper productionOrderMapper;
    private final IProductionOrderRepository productionOrderRepository;
    private final IRawMaterialRepository rawMaterialRepository;

    @Override
    public ProductionOrderDtoResponse createProductionOrder(ProductionOrderDto productionOrderDto) {
       Product product = productRepository.findById(productionOrderDto.productId()).orElseThrow(()->new RuntimeException("product not found"));
       List<BillOfMaterial> billOfMaterials = product.getBillOfMaterials();
       for(BillOfMaterial billOfMaterial:billOfMaterials){
           RawMaterial rawMaterial = billOfMaterial.getRawMaterial();
           Integer quantity = billOfMaterial.getQuantity() * productionOrderDto.quantity();

           Integer stock = rawMaterial.getStock();
           if(quantity > stock){
               throw new RuntimeException("la quantite est superieure au stock");
           }
       }

       for(BillOfMaterial billOfMaterial:billOfMaterials){
           RawMaterial rawMaterial = billOfMaterial.getRawMaterial();
           Integer quantity = billOfMaterial.getQuantity() * productionOrderDto.quantity();
           rawMaterial.setStock(rawMaterial.getStock()-quantity);
           rawMaterialRepository.save(rawMaterial);
       }

       ProductionOrder productionOrder = productionOrderMapper.toEntity(productionOrderDto);
       productionOrder.setProduct(product);
        productionOrder.setEndDate(productionOrder.getStartDate().plusHours((long) product.getProductionTime() * productionOrderDto.quantity()));
        productionOrder.setProductionOrderStatus(ProductionOrderStatus.EN_ATTENTE);
        return productionOrderMapper.toDtoResponse(productionOrderRepository.save(productionOrder));
    }

    @Override
    public List<ProductionOrderDtoResponse> getAllProductionOrders() {
        List<ProductionOrder> productionOrders = productionOrderRepository.findAll() ;
        return productionOrders.stream().map(productionOrderMapper::toDtoResponse).toList();
    }

    @Override
    public ProductionOrderDtoResponse updateProductionOrder(ProductionOrderDto productionOrderDto, Long id) {
        ProductionOrder productionOrder = productionOrderRepository.findById(id).orElseThrow(()->new RuntimeException("production order not found"));
        Product product = productRepository.findById(productionOrderDto.productId()).orElseThrow(()->new RuntimeException("product not found"));
        productionOrder.setProduct(product);
        productionOrder.setQuantity(productionOrderDto.quantity());
        productionOrder.setStartDate(productionOrderDto.startDate());
        productionOrder.setEndDate(productionOrder.getStartDate().plusHours((long) product.getProductionTime() * productionOrderDto.quantity()));
        productionOrder.setProductionOrderStatus(ProductionOrderStatus.EN_ATTENTE);
        ProductionOrder savingProductionOrder = productionOrderRepository.save(productionOrder);

        return productionOrderMapper.toDtoResponse(savingProductionOrder);
    }

    @Override
    public ProductionOrderDtoResponse getProductionOrderById(Long id) {
        ProductionOrder productionOrder = productionOrderRepository.findById(id).orElseThrow(()->new RuntimeException("the production order not found"));
        return productionOrderMapper.toDtoResponse(productionOrder);
    }


    @Override
    public MessageResponse bloqueOrder(Long id) {
        ProductionOrder productionOrder = productionOrderRepository.findById(id).orElseThrow(()->new RuntimeException("production order not found"));
        if(productionOrder.getProductionOrderStatus().equals(ProductionOrderStatus.EN_ATTENTE)){
            productionOrder.setProductionOrderStatus(ProductionOrderStatus.BLOQUE);
            productionOrderRepository.save(productionOrder);
            return new MessageResponse("the production order bloqued succefully");
        }
        return new MessageResponse("this production order is not pending");
    }

    @Override
    public List<ProductionOrderDtoResponse> getProductionOrdersByStatus(ProductionOrderStatus status) {
        List<ProductionOrder> productionOrders = productionOrderRepository.findProductionOrderByProductionOrderStatus(status);
        return productionOrders.stream().map(productionOrderMapper::toDtoResponse).toList();
    }

    @Override
    public long tempsEstime(Long id){
        ProductionOrder productionOrder = productionOrderRepository.findById(id).orElseThrow(()->new RuntimeException("production order not found"));
        return ChronoUnit.MINUTES.between(productionOrder.getStartDate(), productionOrder.getEndDate());
    }
}
