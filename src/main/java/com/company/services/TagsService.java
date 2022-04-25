package com.company.services;

import com.company.entity.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagsService {
    Page<Tags> getAllTags(Pageable pageable);

    Tags save(Tags tags);

    Tags findById(Long id);

    Tags update(Tags tags, Long id);

    void delete(Long id);
}
