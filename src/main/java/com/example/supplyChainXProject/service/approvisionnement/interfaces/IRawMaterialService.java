package com.example.supplyChainXProject.service.approvisionnement.interfaces;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.RawMaterialDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.rawMaterialResponse.RawMaterialDtoResponse;

import java.util.List;

public interface IRawMaterialService {
    RawMaterialDtoResponse createRawMaterial(RawMaterialDto rawMaterialDto);
    List<RawMaterialDtoResponse> getAllRawMaterials();
    RawMaterialDtoResponse updateRawMaterial(RawMaterialDto rawMaterialDto, Long id);
    RawMaterialDtoResponse getRawMaterialById(Long id);
    MessageResponse deleteRawMaterial(Long id);
    List<RawMaterialDtoResponse> filterByCritiqueStock(Integer critiqueStock);
}
