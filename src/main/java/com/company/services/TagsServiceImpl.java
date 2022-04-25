package com.company.services;

import com.company.entity.Tags;
import com.company.repository.TagsRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TagsServiceImpl implements TagsService{

    private final TagsRespository tagsRespository;

    public TagsServiceImpl(TagsRespository tagsRespository) {
        this.tagsRespository = tagsRespository;
    }

    @Override
    public Page<Tags> getAllTags(Pageable pageable) {
        return tagsRespository.findAll(pageable);
    }

    @Override
    public Tags save(Tags tags) {
        return tagsRespository.save(tags);
    }

    @Override
    public Tags findById(Long id) {
        return tagsRespository.getById(id);
    }

    @Override
    public Tags update(Tags tags, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
       Tags tags = tagsRespository.getById(id);
       tagsRespository.delete(tags);
    }
}
