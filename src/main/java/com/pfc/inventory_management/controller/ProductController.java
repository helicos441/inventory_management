package com.pfc.inventory_management.controller;

import com.pfc.inventory_management.dto.*;
import com.pfc.inventory_management.entity.Product;
import com.pfc.inventory_management.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> p = productService.getProductById(id);
        return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/searchName")
    public ResponseEntity<Iterable<Product>> searchProductsByName(@RequestBody @Valid SearchProductNameDto searchProductNameDto) {
        return ResponseEntity.status(200).body(productService.searchProductsByName(searchProductNameDto.productName()));
    }

    @GetMapping("/searchPrice")
    public ResponseEntity<Iterable<Product>> searchProductsByPrice(@RequestBody @Valid SearchProductPricesDto dto) {
        return ResponseEntity.status(200).body(productService.searchProductsByPrice(dto.minPrice(), dto.maxPrice()));
    }

    @GetMapping("/searchCategory")
    public ResponseEntity<Iterable<Product>> getProductsByCategoryName(@RequestBody @Valid SearchCategoryDto dto) {
        return ResponseEntity.status(200).body(productService.searchByCategory(dto.categoryName()));
    }

    @GetMapping("/searchSupplier")
    public ResponseEntity<Iterable<Product>> getProductsBySupplierName(@RequestBody @Valid SearchSupplierDto dto) {
        return ResponseEntity.status(200).body(productService.searchBySupplier(dto.supplierName()));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDto dto) {
        Product p = productService.createProduct(dto);
        URI location = URI.create("/api/products/" + p.getId());
        return ResponseEntity.created(location).body(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto dto) {
        Product p = productService.updateProduct(id, dto);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
