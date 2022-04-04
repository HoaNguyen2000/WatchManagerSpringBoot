package com.company.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @Secured({"ROLE_USER", "ROLE_SUPER_ADMIN", "ROLE_ADMIN"})
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @Secured("ROLE_SUPER_ADMIN")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    public String adminAccess() {
        return "Admin Board.";
    }
}