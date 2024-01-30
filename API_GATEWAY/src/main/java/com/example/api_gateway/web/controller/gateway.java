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

    @GetMapping("/Inventaire")
    private String TestInventaire(){
        String url = "http://localhost:8081/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/Boutique")
    private String TestBoutique(){
        String url = "http://localhost:8082/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/Joueur")
    private String TestJoueur(){
        String url = "http://localhost:8083/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/Coffre")
    private String TestCoffre(){
        String url = "http://localhost:8085/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/Cave")
    private String TestCave(){
        String url = "http://localhost:8086/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/Monstre")
    private String TestMonstre(){
        String url = "http://localhost:8087/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/Combat")
    private String TestCombat(){
        String url = "http://localhost:8088/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/Log")
    private String TestLog(){
        String url = "http://localhost:8089/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }


}