package com.example.inventaire.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class InventaireController {
    @GetMapping("/main")
    public String main(){
        return "Hello Inventaire !";
    }
}
