package com.company.inventory.services;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.models.Category;
import com.company.inventory.response.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements IcategoryService{
    @Autowired
    private ICategoryDao categoryDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {
        CategoryResponseRest response = new CategoryResponseRest();
        try {
            List<Category> categori = (List<Category>) categoryDao.findAll();
            response.getCategoryResponse().setCategory(categori);
            response.setMetadata("Respuesta Ok", "00", "Respuesta exitoxa");
        }catch (Exception e) {
            response.setMetadata("Respuesta no ok", "-1", "Error al consultar");
            e.getStackTrace();
            return  new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return  new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }


}
