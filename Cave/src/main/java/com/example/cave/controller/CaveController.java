package com.example.cave.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CaveController {
    @GetMapping("/main")
    public String main(){
        return "Hello Cave !";
    }
}