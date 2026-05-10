package com.pfc.inventory_management.repository;

import com.pfc.inventory_management.entity.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Transactional
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    public void testFindAll()
    {
        var products = productRepository.findAll();

        assertEquals(2, products.size());
    }

    @Test
    public void TestFindProductById()
    {
        var product = productRepository.findById(1L);

        assertTrue(product.isPresent());
        assertEquals(1L, product.get().getId());
        assertEquals("Mary Lester", product.get().getName());
    }

    @Test
    public void testFindByPrice()
    {
        var products = productRepository.findByPriceBetween(new BigDecimal("5.0"), new BigDecimal("20.0"));
        assertEquals(1, products.size());
    }

    @Test
    public void testFindByCategory()
    {
        var products = productRepository.findByCategoryName("Tools");
        assertEquals(1, products.size());
    }

    @Test
    public void testFindBySupplier()
    {
        var products = productRepository.findBySupplierName("Bricomarché");
        assertEquals(1, products.size());
    }

    @Test
    public void createProduct()
    {
        var product = new Product();
        product.setName("Test");
        product.setPrice(new BigDecimal("10.0"));
        product.setDescription("Test product");
        product.setQuantity(1);
        product.setCategory(categoryRepository.findById(1L).orElse(null));
        product.setSupplier(supplierRepository.findById(1L).orElse(null));
        productRepository.save(product);

        var products = productRepository.findAll();
        assertEquals(3, products.size());
    }

    @Test
    public void updateProduct()
    {
        var product = productRepository.findById(1L).orElse(null);
        assert product != null;
        product.setName("Updated Test");
        productRepository.save(product);

        var p = productRepository.findById(1L).orElse(null);
        assert p != null;
        assertEquals("Updated Test", p.getName());
    }

    @Test
    public void deleteProduct()
    {
        productRepository.deleteById(1L);

        var products = productRepository.findAll();
        assertEquals(1, products.size());
    }

}
