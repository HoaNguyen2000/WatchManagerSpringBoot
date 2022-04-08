package com.company.services;

import com.company.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product save(Product product);

    Product findById(Long id);

    Product update(Product product, Long id);

    void delete(Long id);
}
