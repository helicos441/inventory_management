package com.pfc.inventory_management.controller;

import com.pfc.inventory_management.dto.ProductDto;
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
    public ResponseEntity<Product> getCategoryById(@PathVariable Long id) {
        Optional<Product> p = productService.getProductById(id);
        return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
