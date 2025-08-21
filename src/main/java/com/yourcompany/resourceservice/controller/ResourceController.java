package com.yourcompany.resourceservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/hello")
    public String hello() {
        return "Resource Service is up!";
    }
}
