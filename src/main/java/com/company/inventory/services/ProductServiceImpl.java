package com.company.inventory.services;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.dao.IProductDao;
import com.company.inventory.models.Category;
import com.company.inventory.models.Product;
import com.company.inventory.response.ProductResponseRest;
import com.company.inventory.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private IProductDao productDao;

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try {
            //Search category to set in product object
            Optional<Category> category = this.categoryDao.findById(categoryId);
            if (category.isPresent()) {
                product.setCategory(category.get());
            } else {
                response.setMetadata("respuesta no ok", "-1", "Categoria encontrada asocianda al producto");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            //Save the product
            Product productSave = this.productDao.save(product);
            if (productSave != null) {
                list.add(productSave);
                response.getProductResponse().setProducts(list);
                response.setMetadata("respuesta ok", "00", "Producto guardado");
            } else {
                response.setMetadata("respuesta no ok", "-1", "Producto no guardado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.getMessage();
            response.setMetadata("respuesta no ok", "-1", "Error al guardar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchById(Long id) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try {
            //Search product By Id
            Optional<Product> product = this.productDao.findById(id);
            if (product.isPresent()) {
                byte [] imageDescompresed = Util.decompressZLib(product.get().getPicture());
                product.get().setPicture(imageDescompresed);
                list.add(product.get());
                response.getProductResponse().setProducts(list);
                response.setMetadata("Respuesta ok", "00", "Producto encontrado");
            } else {
                response.setMetadata("respuesta no ok", "-1", "Producto no encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.getMessage();
            response.setMetadata("respuesta no ok", "-1", "Error al guardar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> findByName(String name) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        List<Product> listAux = new ArrayList<>();

        try {
            //Search product By name
            listAux = this.productDao.findByNameContainingIgnoreCase(name);
            if (listAux.size() > 0) {
                listAux.stream().forEach((product) -> {
                    byte [] imageDescompresed = Util.decompressZLib(product.getPicture());
                    product.setPicture(imageDescompresed);
                    list.add(product);
                });

                response.getProductResponse().setProducts(list);
                response.setMetadata("Respuesta ok", "00", "Productos encontrados");
            } else {
                response.setMetadata("respuesta no ok", "-1", "Productos no encontrados");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.getMessage();
            response.setMetadata("respuesta no ok", "-1", "Error al buscar producto por nombre");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
