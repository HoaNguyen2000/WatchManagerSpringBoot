package com.company.services;

import com.company.entity.Brands;
import com.company.exception.ResourceNotFoundExeption;
import com.company.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BrandsServiceImpl implements BrandsService {

    BrandRepository brandRepository;

    @Override
    public Page<Brands> getAllBrands(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Brands save(Brands brands) {
        return brandRepository.save(brands);
    }

    @Override
    public Brands findById(Long id) {
        return brandRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundExeption("Cannot found the resource"));
    }

    @Override
    @Transactional
    public Brands update(Brands brands, Long id) {
        Brands brand = findById(id);
        brand.setName(brands.getName());
        brand.setSlug(brands.getSlug());
        return brandRepository.save(brand);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Brands brand = findById(id);
        brandRepository.delete(brand);
    }
}
