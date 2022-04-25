package com.company.services;

import com.company.entity.SiteInfo;
import com.company.repository.SiteInfoRespository;
import org.springframework.stereotype.Service;

@Service
public class SiteInfoServiceImpl implements SiteInfoService{

    private final SiteInfoRespository siteInfoRespository;

    public SiteInfoServiceImpl(SiteInfoRespository siteInfoRespository) {
        this.siteInfoRespository = siteInfoRespository;
    }

    @Override
    public SiteInfo update(SiteInfo siteInfo) {
        long id= 1;
        SiteInfo siteInfoUpdate = siteInfoRespository.getById(id);
        siteInfoUpdate.setAuthor(siteInfo.getAuthor());
        siteInfoUpdate.setEmail(siteInfo.getEmail());
        siteInfoUpdate.setAddress(siteInfo.getAddress());
        siteInfoUpdate.setPhone(siteInfo.getPhone());
        siteInfoUpdate.setLogoLink(siteInfo.getLogoLink());
        return siteInfoRespository.save(siteInfoUpdate);
    }
}
