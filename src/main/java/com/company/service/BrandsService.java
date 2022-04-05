package com.company.service;

import com.company.entity.Brands;

import java.util.List;

public interface BrandsService {
    List<Brands> getAllBrands();

    Brands save(Brands brands);

    Brands findById(Long id);

    Brands update(Brands brands, Long id);

    void delete(Long id);
}
