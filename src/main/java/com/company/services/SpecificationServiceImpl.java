package com.company.services;

import com.company.entity.Specification;
import com.company.exception.ResourceNotFoundExeption;
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
    public Specification save(Specification watches) {
        return specificationRepository.save(watches);
    }

    @Override
    public Specification findById(Long id) {
        return specificationRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExeption("Cannot found the resource"));
    }

    @Override
    @Transactional
    public Specification update(Specification specification, Long id) {
        Specification spec = findById(id);
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
        Specification specification = findById(id);
        specificationRepository.delete(specification);
    }
}
