package com.example.supplyChainXProject.service.production;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierOrder.SupplyOrderDtoResponse;
import com.example.supplyChainXProject.dto.production.ProductDto;
import com.example.supplyChainXProject.dto.production.response.ProductResponseDto;
import com.example.supplyChainXProject.entity.approvisionnement.RawMaterial;
import com.example.supplyChainXProject.entity.approvisionnement.RawMaterialSupplyOrder;
import com.example.supplyChainXProject.entity.approvisionnement.Supplier;
import com.example.supplyChainXProject.entity.approvisionnement.SupplyOrder;
import com.example.supplyChainXProject.entity.production.BillOfMaterial;
import com.example.supplyChainXProject.entity.production.Product;
import com.example.supplyChainXProject.enums.SupplyOrderStatus;
import com.example.supplyChainXProject.mapper.production.IProductMapper;
import com.example.supplyChainXProject.repository.approvisionnement.IRawMaterialRepository;
import com.example.supplyChainXProject.repository.production.IProductRepository;
import com.example.supplyChainXProject.service.production.interfaces.IProductService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private IRawMaterialRepository rawMaterialRepository;
    private IProductRepository productRepository;
    private IProductMapper productMapper;


    @Override
    public ProductResponseDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.name());
        product.setProductionTime(productDto.productionTime());
        product.setCost(productDto.cost());
        product.setStock(productDto.stock());

        for (Map.Entry<Long, Integer> entry : productDto.rawMaterials().entrySet()) {
            Long materialId = entry.getKey();
            Integer quantity = entry.getValue();
            RawMaterial material = rawMaterialRepository.findById(materialId).orElseThrow(() -> new RuntimeException("Raw Material not found"));
            BillOfMaterial billOfMaterial = new BillOfMaterial();
            billOfMaterial.setRawMaterial(material);
            billOfMaterial.setQuantity(quantity);
            billOfMaterial.setProduct(product);
            product.addBillOfMaterial(billOfMaterial);
        }

        Product savingProduct =  productRepository.save(product);
        return productMapper.toDtoResponse(savingProduct);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toDtoResponse).toList();
    }

    @Override
    public ProductResponseDto updateProduct(ProductDto productDto, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
        product.setName(productDto.name());
        product.setProductionTime(productDto.productionTime());
        product.setCost(productDto.cost());
        product.setStock(productDto.stock());
        product.getBillOfMaterials().clear();
        for (Map.Entry<Long, Integer> entry : productDto.rawMaterials().entrySet()) {
            Long materialId = entry.getKey();
            Integer quantity = entry.getValue();
            RawMaterial material = rawMaterialRepository.findById(materialId).orElseThrow(() -> new RuntimeException("Raw Material not found"));
            BillOfMaterial billOfMaterial = new BillOfMaterial();
            billOfMaterial.setRawMaterial(material);
            billOfMaterial.setQuantity(quantity);
            billOfMaterial.setProduct(product);
            product.addBillOfMaterial(billOfMaterial);
        }
        Product updatedProduct = productRepository.save(product);
        return productMapper.toDtoResponse(updatedProduct);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("product Not Found"));
        return productMapper.toDtoResponse(product);
    }

    @Override
    public MessageResponse deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("product Not Found"));
        if(!product.getOrders().isEmpty()){
            return new MessageResponse("you cannot delete this product");
        }
        productRepository.delete(product);
        return new MessageResponse("product deleted successfuly");
    }


    @Override
    public ProductResponseDto searchByName(String name){
        Product product = productRepository.findProductByName(name);
        if(product == null){
            throw new RuntimeException("product not found");
        }
        return productMapper.toDtoResponse(product);
    }

}
