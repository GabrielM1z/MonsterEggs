package com.example.boutique.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BoutiqueController {
    @GetMapping("/main")
    public String main(){
        return "Hello Boutique !";
    }
}
