package com.example.demohelloworld.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/helloTest")
    public String helloTest(){
        return "Hello World !";
    }
}
