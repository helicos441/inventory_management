package com.pfc.inventory_management.service;

import java.util.Optional;

import com.pfc.inventory_management.entity.Supplier;
import com.pfc.inventory_management.repository.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Iterable<Supplier> getAllCategories() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getCategoryById(Long id) {
        return supplierRepository.findById(id);
    }

    public Supplier createSupplier(String name) {
        Supplier supplier = new Supplier();
        supplier.setName(name);
        supplierRepository.save(supplier);
        return supplier;
    }

    public Supplier updateSupplier(Long id, String name) {
        Supplier supplier = supplierRepository.findById(id).orElse(new Supplier());
        supplier.setName(name);
        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
