package com.example.supplyChainXProject.controller.user;

import com.example.supplyChainXProject.dto.user.UserDto;
import com.example.supplyChainXProject.enums.Role;
import com.example.supplyChainXProject.service.user.IUserService;
import com.example.supplyChainXProject.utils.Validation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Tag(name = "crud users", description = "crud")
public class UserController {
    private final IUserService userService;

    @PostMapping("create/role/{role}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<?> createUser(@PathVariable("role") Role role,@Valid  @RequestBody UserDto userDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Validation.getValidationErrors(result));
        }
        return ResponseEntity.ok(userService.createUser(userDto, role));
    }

    @PostMapping("/{id}/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateRole(@PathVariable("id") Long id, @PathVariable("role") Role role){
        return ResponseEntity.ok(userService.updateRole(id,role));
    }
}
