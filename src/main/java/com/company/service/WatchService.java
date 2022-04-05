package com.company.service;

import com.company.entity.Watch;

import java.util.List;

public interface WatchService {
    List<Watch> findAllWatches();

    Watch save(Watch watches);

    Watch findById(Long id);

    Watch update(Watch watches, Long id);

    void delete(Long id);
}
