package com.company.services;

import com.company.entity.Specification;

public interface SpecificationService {
    Specification save(Specification watches);

    Specification findById(Long id);

    Specification update(Specification specification, Long id);

    void delete(Long id);
}
