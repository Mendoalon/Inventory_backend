package com.company.inventory.dao;

import com.company.inventory.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductDao extends CrudRepository<Product, Long> {
}
