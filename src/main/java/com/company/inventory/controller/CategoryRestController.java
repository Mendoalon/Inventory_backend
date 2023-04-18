package com.company.inventory.controller;

import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.IcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
    @Autowired
    private IcategoryService service;

    /**
     * Optener todas las categorias.
     * @return
     */
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategories(){
        ResponseEntity<CategoryResponseRest> response = service.search();
        return response;
    }

    /**
     *Optener todas la cotegoria por id.
     * @param id
     * @return
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response = service.searchById(id);
        return response;
    }


}
