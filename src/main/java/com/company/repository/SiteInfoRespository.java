package com.company.repository;

import com.company.entity.SiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteInfoRespository extends JpaRepository<SiteInfo, Long> {
}
