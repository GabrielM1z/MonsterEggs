package com.example.coffre.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CoffreController {
    @GetMapping("/main")
    public String main(){
        return "Hello Coffre !";
    }
}
