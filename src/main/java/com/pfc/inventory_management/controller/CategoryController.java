package com.pfc.inventory_management.controller;

import com.pfc.inventory_management.dto.CategoryDto;
import com.pfc.inventory_management.entity.Category;
import com.pfc.inventory_management.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable  Long id) {
        Optional<Category> c = categoryService.getCategoryById(id);
        return c.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryDto dto) {
        Category c = categoryService.createCategory(dto.name());
        URI location = URI.create("/api/categories/" + c.getId());
        return ResponseEntity.created(location).body(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDto dto) {
        Category c = categoryService.updateCategory(id, dto.name());
        return ResponseEntity.ok(c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
