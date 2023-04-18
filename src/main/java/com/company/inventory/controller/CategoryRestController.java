package com.company.inventory.controller;

import com.company.inventory.models.Category;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.IcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
    @Autowired
    private IcategoryService service;

    /**
     * Metodo Optener todas las categorias.
     *
     * @return
     */
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategories() {
        ResponseEntity<CategoryResponseRest> response = this.service.search();
        return response;
    }

    /**
     * Metodo Optener todas la cotegoria por id.
     *
     * @param id
     * @return
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = this.service.searchById(id);
        return response;
    }

    /**
     * Metodo Guarda una categoria
     *
     * @param category
     * @return
     */
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category) {
        ResponseEntity<CategoryResponseRest> response = this.service.save(category);
        return response;
    }

    /**
     * Metodo para actualizar una categoria
     *
     * @param category
     * @param id
     * @return
     */
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category, @PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = this.service.apdate(category, id);
        return response;
    }

    /**
     * Metodo para eliminar categoria por id.
     *
     * @param id
     * @return
     */
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = this.service.delete(id);
        return response;
    }

}
