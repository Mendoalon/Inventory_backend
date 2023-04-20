package com.company.inventory.dao;

import com.company.inventory.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductDao extends CrudRepository<Product, Long> {

    @Query("select p from Product p where p.name like %:name%")
    List<Product> findByNameLike(@Param("name") String name);

    List<Product> findByNameContainingIgnoreCase(String name);
}
