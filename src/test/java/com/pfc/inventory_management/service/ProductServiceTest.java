package com.pfc.inventory_management.service;

import com.pfc.inventory_management.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testFindAll()
    {
        var products = productService.getAllProducts();

        assertEquals(2, products.size());
    }

}
