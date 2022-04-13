package com.company.services;

import com.company.entity.Brands;
import com.company.exception.ResourceNotFoundExeption;
import com.company.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandsServiceImpl implements BrandsService {

    BrandRepository brandRepository;

    @Override
    public List<Brands> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brands save(Brands brands) {
        return brandRepository.save(brands);
    }

    @Override
    public Brands findById(Long id) {
        return brandRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundExeption("Cannot found the resource"));
    }

    @Override
    public Brands update(Brands brands, Long id) {
        Brands brand = findById(id);
        brand.setName(brands.getName());
        brand.setSlug(brands.getSlug());
        return brandRepository.save(brand);
    }

    @Override
    public void delete(Long id) {
        Brands brand = findById(id);
        brandRepository.delete(brand);
    }
}
