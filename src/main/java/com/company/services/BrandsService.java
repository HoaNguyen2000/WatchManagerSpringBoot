package com.company.services;

import com.company.entity.Brands;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BrandsService {
    Page<Brands> getAllBrands(Pageable pageable);

    Brands save(Brands brands);

    Brands findById(Long id);

    Brands update(Brands brands, Long id);

    void delete(Long id);
}
