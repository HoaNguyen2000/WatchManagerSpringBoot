package com.company.repository;

import com.company.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRespository extends JpaRepository<Tags, Long> {

}
