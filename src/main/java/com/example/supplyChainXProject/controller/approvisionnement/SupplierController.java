package com.example.supplyChainXProject.controller.approvisionnement;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import com.example.supplyChainXProject.dto.approvisionnement.SupplierDto;
import com.example.supplyChainXProject.dto.approvisionnement.response.supplierResponse.SupplierDtoResponse;
import com.example.supplyChainXProject.service.approvisionnement.interfaces.ISupplierService;
import com.example.supplyChainXProject.utils.Validation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "supplier crud", description = "crud")
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final ISupplierService supplierService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('GESTIONNAIRE_APPROVISIONNEMENT')")
    public ResponseEntity<?> getSupplierById(@PathVariable("id") Long id){
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('GESTIONNAIRE_APPROVISIONNEMENT')")
    public ResponseEntity<?> createSupplier(@Valid @RequestBody SupplierDto supplierDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        return ResponseEntity.ok(supplierService.createSupplier(supplierDto));
    }

    @GetMapping
    @PreAuthorize("hasRole('SUPERVISEUR_LOGISTIQUE')")
    public ResponseEntity<List<SupplierDtoResponse>> getAllSuppliers(){
        List<SupplierDtoResponse> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('GESTIONNAIRE_APPROVISIONNEMENT')")
    public ResponseEntity<?> updateSupplier(@PathVariable("id") Long id, @Valid @RequestBody SupplierDto supplierDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        SupplierDto supplier = supplierService.updateSupplier(supplierDto, id);
        return ResponseEntity.ok(supplier);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GESTIONNAIRE_APPROVISIONNEMENT')")
    public ResponseEntity<MessageResponse> deleteSupplier(@PathVariable("id") Long id){
        MessageResponse messageResponse = supplierService.deleteSupplier(id);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/search/{firstName}/{lastName}")
    @PreAuthorize("hasRole('RESPONSABLE_ACHATS')")
    public ResponseEntity<?> searchSupplierByName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        SupplierDtoResponse supplier = supplierService.searchByName(firstName, lastName);
        return ResponseEntity.ok(supplier);
    }

}