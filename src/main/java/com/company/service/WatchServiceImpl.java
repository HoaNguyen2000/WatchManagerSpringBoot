package com.company.service;

import com.company.entity.Watch;
import com.company.exception.ResourceNotFoundExeption;
import com.company.repository.WatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class WatchServiceImpl implements WatchService {

    private final WatchRepository watchRepository;

    @Override
    public List<Watch> findAllWatches() {
        return watchRepository.findAll();
    }

    @Override
    public Watch save(Watch watches) {
        return watchRepository.save(watches);
    }

    @Override
    public Watch findById(Long id) {
        return watchRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundExeption("Cannot found the resource")
        );
    }

    @Override
    public Watch update(Watch watches, Long id) {
        Watch watch = findById(id);
        watch.setName(watches.getName());
        watch.setBrandId(watches.getBrandId());
        watch.setDescription(watches.getDescription());
        watch.setOldPrice(watches.getOldPrice());
        watch.setPrice(watches.getPrice());
        watch.setSlug(watches.getSlug());
        watch.setType(watches.getType());
        return watchRepository.save(watch);
    }

    @Override
    public void delete(Long id) {
        Watch watch = findById(id);
        watchRepository.delete(watch);
    }
}
