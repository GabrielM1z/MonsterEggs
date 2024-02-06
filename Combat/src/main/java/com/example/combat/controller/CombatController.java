package com.example.combat.controller;

import com.example.combat.model.Monstre;
import com.example.combat.service.CombatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(path="/combat")
public class CombatController {

    private final CombatService combatService;

    public CombatController(CombatService combatService) {
        this.combatService = combatService;
    }

    /**
     * Route de vérification service up
     */
    @GetMapping(path="/main")
    public @ResponseBody String main(){
        return "OK";
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody String combat(
            @PathVariable int id
    )
    {
        String url = "http://localhost:8086/monstre/get/"+id;
        RestTemplate restTemplate = new RestTemplate();
        try {
            Monstre monstre = restTemplate.getForObject(url, Monstre.class);
            String str = combatService.combat(monstre);
            return str;
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
        return null;
    }
}
