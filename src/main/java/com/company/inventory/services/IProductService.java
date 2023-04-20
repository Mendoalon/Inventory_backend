package com.company.inventory.services;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.dao.IProductDao;
import com.company.inventory.models.Product;
import com.company.inventory.response.ProductResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public interface IProductService {

    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);
}
