package com.company;

import com.company.services.FileStorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.Resource;


@SpringBootApplication
public class WatchManagerSpringBootApplication {
    @Resource
    FileStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(WatchManagerSpringBootApplication.class, args);
    }

}
