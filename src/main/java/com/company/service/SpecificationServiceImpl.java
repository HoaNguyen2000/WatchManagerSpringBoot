package com.company.service;

import com.company.entity.Specification;
import com.company.exception.ResourceNotFoundExeption;
import com.company.repository.SpecificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpecificationServiceImpl implements SpecificationService{

    private final SpecificationRepository specificationRepository;

    @Override
    public Specification save(Specification watches) {
        return specificationRepository.save(watches);
    }

    @Override
    public Specification findById(Long id) {
        return specificationRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExeption("Cannot found the resource"));
    }

    @Override
    public Specification update(Specification specification, Long id) {
        Specification spec = findById(id);
        spec.setLaunchAnnounced(specification.getLaunchAnnounced());
        spec.setDisplayDimension(specification.getDisplayDimension());
        spec.setDisplayResolution(specification.getDisplayResolution());
        spec.setDisplayType(specification.getDisplayType());
        spec.setDisplaySize(specification.getDisplaySize());
        spec.setBattery(specification.getBattery());
        spec.setColor(specification.getColor());
        return specificationRepository.save(spec);
    }

    @Override
    public void delete(Long id) {
        Specification specification = findById(id);
        specificationRepository.delete(specification);
    }
}
