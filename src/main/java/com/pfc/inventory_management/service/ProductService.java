package com.pfc.inventory_management.service;

import com.pfc.inventory_management.dto.ProductDto;
import com.pfc.inventory_management.entity.Category;
import com.pfc.inventory_management.entity.Product;
import com.pfc.inventory_management.entity.Supplier;
import com.pfc.inventory_management.repository.CategoryRepository;
import com.pfc.inventory_management.repository.ProductRepository;
import com.pfc.inventory_management.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> searchProductsByName(String productName) {
        return productRepository.findByNameContainingIgnoreCase(productName);
    }

    public List<Product> searchProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> searchByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    public List<Product> searchBySupplier(String supplierName) {
        return productRepository.findBySupplierName(supplierName);
    }

    public Product createProduct(ProductDto product) {
        Product newProduct = new Product();
        newProduct.setName(product.name());
        newProduct.setPrice(product.price());
        newProduct.setQuantity(product.quantity());
        newProduct.setDescription(product.description());

        Category cat = categoryRepository.findById(product.categoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        Supplier sup = supplierRepository.findById(product.supplierId()).orElseThrow(() -> new IllegalArgumentException("Supplier not found"));

        newProduct.setCategory(cat);
        newProduct.setSupplier(sup);
        return productRepository.save(newProduct);
    }

    public Product updateProduct(Long id, ProductDto product) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        existingProduct.setName(product.name());
        existingProduct.setPrice(product.price());
        existingProduct.setQuantity(product.quantity());
        existingProduct.setDescription(product.description());

        Category cat = categoryRepository.findById(product.categoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        Supplier sup = supplierRepository.findById(product.supplierId()).orElseThrow(() -> new IllegalArgumentException("Supplier not found"));

        existingProduct.setCategory(cat);
        existingProduct.setSupplier(sup);

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
