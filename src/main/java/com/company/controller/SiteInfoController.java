package com.company.controller;

import com.company.entity.SiteInfo;
import com.company.services.SiteInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/site-info")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN"})
public class SiteInfoController {
    private final SiteInfoService siteInfoService;


    @PutMapping
    public ResponseEntity<SiteInfo> update(@RequestBody SiteInfo siteInfo){
        return ResponseEntity.ok(siteInfoService.update(siteInfo));
    }
}
