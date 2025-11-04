package com.example.supplyChainXProject.controller.approvisionnement;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.RawMaterialDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.rawMaterialResponse.RawMaterialDtoResponse;
import com.example.supplyChainXProject.mapper.approvisionnement.IRawMatrialMapper;
import com.example.supplyChainXProject.service.approvisionnement.interfaces.IRawMaterialService;
import com.example.supplyChainXProject.utils.Validation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@Tag(name = "rawMaterial crud", description = "crud")
@RequestMapping("/api/rawMaterials")
public class RawMaterialController {
    private final IRawMaterialService rawMaterialService;
    private final IRawMatrialMapper rawMatrialMapper;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createRawMaterial(@Valid @RequestBody RawMaterialDto rawMaterialDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        return ResponseEntity.ok(rawMaterialService.createRawMaterial(rawMaterialDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRawMaterialById(@PathVariable("id") Long id){
        RawMaterialDtoResponse rawMaterial = rawMaterialService.getRawMaterialById(id);
        return ResponseEntity.ok(rawMaterial);
    }

    @GetMapping
    public ResponseEntity<List<RawMaterialDtoResponse>> getAllRawMaterials(){
        List<RawMaterialDtoResponse> rawMaterials = rawMaterialService.getAllRawMaterials();
        return ResponseEntity.ok(rawMaterials);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable("id") Long id, @Valid @RequestBody RawMaterialDto rawMaterialDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        return ResponseEntity.ok(rawMaterialService.updateRawMaterial(rawMaterialDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRawMaterial(@PathVariable("id") Long id){
        MessageResponse messageResponse = rawMaterialService.deleteRawMaterial(id);
        return ResponseEntity.ok(messageResponse);
    }


    @GetMapping("/stock/{stockCritique}")
    public ResponseEntity<?> getMaterialsBelowStock(@PathVariable("stockCritique") Integer stockCritique) {
        List<RawMaterialDtoResponse> materials = rawMaterialService.filterByCritiqueStock(stockCritique);
        return ResponseEntity.ok(materials);
    }
}
