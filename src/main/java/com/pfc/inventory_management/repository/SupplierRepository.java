package com.pfc.inventory_management.repository;

import com.pfc.inventory_management.entity.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
}
