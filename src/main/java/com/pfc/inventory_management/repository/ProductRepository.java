package com.pfc.inventory_management.repository;

import com.pfc.inventory_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String productName);
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    List<Product> findByCategoryName(String categoryName);
    List<Product> findBySupplierName(String supplierName);
}
