package com.company.inventory.services;

import com.company.inventory.models.Category;
import com.company.inventory.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface IcategoryService {
    public ResponseEntity<CategoryResponseRest>search();
    public ResponseEntity<CategoryResponseRest>searchById(Long id);
    public ResponseEntity<CategoryResponseRest>save(Category category);

}
