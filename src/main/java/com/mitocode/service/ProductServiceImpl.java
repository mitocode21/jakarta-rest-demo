package com.mitocode.service;

import com.mitocode.model.Product;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class ProductServiceImpl implements ProductService {

    private static List<Product> list = new ArrayList<>();

    @Override
    public Product save(Product p) {
        list.add(p);
        return p;
    }

    @Override
    public Product update(Product p) {
        list = list.stream().map(e -> e.getId().equals(p.getId()) ? p : e).collect(Collectors.toList());
        return p;
    }

    @Override
    public List<Product> listAll() {
        return list;
    }

    @Override
    public Product findById(Integer id) {
        return list.stream().filter(e -> e.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public void delete(Integer id) {
        list.removeIf(e -> e.getId().equals(id));
    }
}
