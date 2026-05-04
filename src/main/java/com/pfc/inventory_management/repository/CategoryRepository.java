package com.pfc.inventory_management.repository;


import com.pfc.inventory_management.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
