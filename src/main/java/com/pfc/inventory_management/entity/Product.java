package com.pfc.inventory_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    // Category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Supplier
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
