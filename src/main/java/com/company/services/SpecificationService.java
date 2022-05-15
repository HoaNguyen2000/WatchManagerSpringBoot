package com.company.services;

import com.company.entity.Product;
import com.company.entity.Specification;

public interface SpecificationService {
    Specification save(Specification specification);

    Specification findByProduct(Product product);

    Specification update(Specification specification, Long id);

    void delete(Long id);
}
