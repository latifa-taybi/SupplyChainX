package com.example.supplyChainXProject.service.approvisionnement;

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
import com.example.supplyChainXProject.service.approvisionnement.interfaces.IRawMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class RawMaterialService implements IRawMaterialService {

    private final IRawMaterialRepository rawMaterialRepository;
    private final IRawMatrialMapper rawMatrialMapper;
    private final ISupplierRepository supplierRepository;
    private final IRawMaterialSupplyOrderRepository rawMaterialSupplyOrderRepository;

    @Override
    public RawMaterialDtoResponse createRawMaterial(RawMaterialDto rawMaterialDto) {
        RawMaterial rawMaterial = rawMatrialMapper.toEntity(rawMaterialDto);
        if (rawMaterialDto.getSupplierIds() != null) {
            List<Supplier> suppliers = supplierRepository.findAllById(rawMaterialDto.getSupplierIds());
            rawMaterial.setSuppliers(suppliers);

            for (Supplier supplier : suppliers) {
                supplier.getRawMaterials().add(rawMaterial);
            }
        }
        RawMaterial savingRawMaterial =  rawMaterialRepository.save(rawMaterial);
        RawMaterialDtoResponse savingRawMaterialDto = rawMatrialMapper.toDtoResponse(savingRawMaterial);
        return savingRawMaterialDto;
    }

    @Override
    public List<RawMaterialDtoResponse> getAllRawMaterials() {
         List<RawMaterial> rawMaterials = rawMaterialRepository.findAll();
         return rawMaterials.stream().map(rawMatrialMapper::toDtoResponse).toList();
    }

    @Override
    public RawMaterialDtoResponse updateRawMaterial(RawMaterialDto rawMaterialDto, Long id) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id).orElseThrow(()->new RuntimeException("raw material Not Found"));;
        rawMaterial.setName(rawMaterialDto.getName());
        rawMaterial.setStock(rawMaterialDto.getStock());
        rawMaterial.setStockMin(rawMaterialDto.getStockMin());
        rawMaterial.setUnit(rawMaterialDto.getUnit());
        if (rawMaterialDto.getSupplierIds() != null) {
            for (Supplier oldSupplier : rawMaterial.getSuppliers()) {
                oldSupplier.getRawMaterials().remove(rawMaterial);
            }

            List<Supplier> newSuppliers = supplierRepository.findAllById(rawMaterialDto.getSupplierIds());
            rawMaterial.setSuppliers(newSuppliers);
            for (Supplier newSupplier : newSuppliers) {
                newSupplier.getRawMaterials().add(rawMaterial);
            }
        }
        RawMaterial savingRawMaterial =  rawMaterialRepository.save(rawMaterial);
        RawMaterialDtoResponse savingRawMaterialDto = rawMatrialMapper.toDtoResponse(savingRawMaterial);
        return savingRawMaterialDto;
    }

    @Override
    public RawMaterialDtoResponse getRawMaterialById(Long id) {
        RawMaterial rawMaterial =  rawMaterialRepository.findById(id).orElseThrow(()->new RuntimeException("raw material Not Found"));
        return rawMatrialMapper.toDtoResponse(rawMaterial);
    }

    @Override
    public MessageResponse deleteRawMaterial(Long id) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id).orElseThrow(() -> new RuntimeException("raw material not found" ));

        if (rawMaterial.getSuppliers() != null && !rawMaterial.getSuppliers().isEmpty()) {
            return new MessageResponse("tu ne peux pas supprimer cette matiere premier  car il est lier avec le fournisseur");
        }

        for(RawMaterialSupplyOrder r:rawMaterialSupplyOrderRepository.findAll()){
            if(rawMaterial.equals(r.getRawMaterial())){
                return new MessageResponse("you can t delete this raw material");
            }
        }
        rawMaterialRepository.delete(rawMaterial);
        return new MessageResponse("The raw material was deleted successfully");
    }

    @Override
    public List<RawMaterialDtoResponse> filterByCritiqueStock(Integer critiqueStock){
        List<RawMaterial> rawMaterials = rawMaterialRepository.findRawMaterialByStockLessThanEqual(critiqueStock);
        return rawMaterials.stream().map(rawMatrialMapper::toDtoResponse).toList();
    }
}
