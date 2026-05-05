package com.pfc.inventory_management.controller;

import com.pfc.inventory_management.dto.SupplierDto;
import com.pfc.inventory_management.entity.Supplier;
import com.pfc.inventory_management.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Supplier>> getAllSuppliers() {
        return ResponseEntity.status(200).body(supplierService.getAllSuppliers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable  Long id) {
        Optional<Supplier> s = supplierService.getSupplierById(id);
        return s.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody @Valid SupplierDto dto) {
        Supplier s = supplierService.createSupplier(dto.name());
        URI location = URI.create("/api/suppliers/" + s.getId());
        return ResponseEntity.created(location).body(s);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody @Valid SupplierDto dto) {
        Supplier s = supplierService.updateSupplier(id, dto.name());
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}
