package com.example.api_gateway.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class gateway {

    private final Map<String, Integer> liste = new HashMap<>();

    public gateway() {
        liste.put("API_Gateway", 8080);
        liste.put("Boutique", 8081);
        liste.put("Cave", 8082);
        liste.put("Coffre", 8083);
        liste.put("Combat", 8084);
        liste.put("Joueur", 8085);
        liste.put("Monstre", 8086);
        liste.put("Log", 8087);


        String url = "http://localhost:8085/inventaire/create/dollards/100";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
    }

    private String performRequest(String url) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            // Gérer l'erreur ici (vous pouvez simplement imprimer le message d'erreur)
            return "Erreur";
        }
    }


    @GetMapping("/")
    public String index() {
        int valeur = liste.get("API_Gateway");
        String url = "http://localhost:" + valeur + "/front/index.html";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
    @GetMapping("/Test")
    private String ResultTest(){
        int valeur = liste.get("Boutique");
        return String.valueOf(valeur);
    }

    @GetMapping("/Inventaire")
    private String TestInventaire(){
        int valeur = liste.get("Inventaire");
        String url = "http://localhost:" + valeur + "/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/Boutique")
    private String TestBoutique(){
        int valeur = liste.get("Boutique");
        String url = "http://localhost:" + valeur + "/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/Joueur")
    private String TestJoueur(){
        int valeur = liste.get("Joueur");
        String url = "http://localhost:" + valeur + "/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/Coffre")
    private String TestCoffre(){
        int valeur = liste.get("Coffre");
        String url = "http://localhost:" + valeur + "/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/Cave")
    private String TestCave(){
        int valeur = liste.get("Cave");
        String url = "http://localhost:" + valeur + "/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/Monstre")
    private String TestMonstre(){
        int valeur = liste.get("Monstre");
        String url = "http://localhost:" + valeur + "/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/Combat")
    private String TestCombat(){
        int valeur = liste.get("Combat");
        String url = "http://localhost:" + valeur + "/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/Log")
    private String TestLog(){
        int valeur = liste.get("Log");
        String url = "http://localhost:" + valeur + "/main";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/API/Boutique/BuyItem/{itemId}")
    private String BuyItem(@PathVariable String itemId) {
        int valeur = liste.get("Boutique");
        String url = "http://localhost:" + valeur + "/BuyItem/" + itemId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
    @GetMapping("/API/Boutique/CreateItem")
    private String CreateItem() {
        int valeur = liste.get("Boutique");
        String url = "http://localhost:" + valeur + "/boutique/refresh";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/API/Boutique/Get")
    private String GetShop() {
        int valeur = liste.get("Boutique");
        String url = "http://localhost:" + valeur + "/boutique/all";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/API/Monstre/Create")
    private String CreateMonstre() {
        int valeur = liste.get("Monstre");
        String url = "http://localhost:" + valeur + "/creation";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/API/Joueur/GetDollards")
    private String CreateDollards() {
        int valeur = liste.get("Joueur");
        String url = "http://localhost:" + valeur + "/inventaire/get/dollards";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}