package com.company.services;

import com.company.entity.Product;
import com.company.entity.Specification;
import com.company.repository.SpecificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SpecificationServiceImpl implements SpecificationService{

    private final SpecificationRepository specificationRepository;

    @Override
    @Transactional
    public Specification save(Specification specification) {
        return specificationRepository.save(specification);
    }

    @Override
    public Specification findByProduct(Product product) {
        return specificationRepository.findByProduct(product);
    }

    @Override
    @Transactional
    public Specification update(Specification specification, Long id) {
        Specification spec = specificationRepository.getById(id);
        spec.setProduct(spec.getProduct());
        spec.setLaunchAnnounced(specification.getLaunchAnnounced());
        spec.setDisplayType(specification.getDisplayType());
        spec.setDisplaySize(specification.getDisplaySize());
        spec.setBattery(specification.getBattery());
        return specificationRepository.save(spec);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Specification specification = specificationRepository.getById(id);
        specificationRepository.delete(specification);
    }
}
