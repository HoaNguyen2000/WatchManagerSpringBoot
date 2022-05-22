package com.company.services;

import com.company.entity.SiteInfo;
import com.company.repository.SiteInfoRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SiteInfoServiceImpl implements SiteInfoService {

    private final SiteInfoRespository siteInfoRespository;

    public SiteInfoServiceImpl(SiteInfoRespository siteInfoRespository) {
        this.siteInfoRespository = siteInfoRespository;
    }

    @Override
    public SiteInfo getSiteInfo() {
        return siteInfoRespository.getById(1L);
    }

    @Transactional
    @Override
    public SiteInfo update(SiteInfo siteInfo) {
        SiteInfo siteInfoUpdate = siteInfoRespository.getById(1L);
        siteInfoUpdate.setAuthor(siteInfo.getAuthor());
        siteInfoUpdate.setEmail(siteInfo.getEmail());
        siteInfoUpdate.setAddress(siteInfo.getAddress());
        siteInfoUpdate.setPhone(siteInfo.getPhone());
        siteInfoUpdate.setLogoLink(siteInfo.getLogoLink());
        return siteInfoRespository.save(siteInfoUpdate);
    }
}
