package com.example.api_gateway.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class gateway {

    @GetMapping("/")
    public String index() {
        String url = "http://localhost:8080/front/index.html";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
    @GetMapping("/Test")
    private String ResultTest(){
        String url = "http://localhost:8079/helloTest";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}