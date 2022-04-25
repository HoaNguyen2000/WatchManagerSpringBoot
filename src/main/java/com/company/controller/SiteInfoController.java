package com.company.controller;

import com.company.entity.SiteInfo;
import com.company.services.SiteInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/site-info")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SiteInfoController {
    private final SiteInfoService siteInfoService;

    @PutMapping
    public ResponseEntity<SiteInfo> update(@RequestBody SiteInfo siteInfo){
        return ResponseEntity.ok(siteInfoService.update(siteInfo));

    }
}
