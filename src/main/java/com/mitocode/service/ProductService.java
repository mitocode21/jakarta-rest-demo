package com.mitocode.service;

import com.mitocode.model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product p);
    Product update(Product p);
    List<Product> listAll();
    Product findById(Integer id);
    void delete(Integer id);
}
