package com.example.combat.service;

import com.example.combat.model.Monstre;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CombatService {

    public String combat(Monstre monstreAttaquant)
    {
        String url = "http://localhost:8086/monstre/getRandomMonstre";
        RestTemplate restTemplate = new RestTemplate();
        try {
            Monstre monstreDefense = restTemplate.getForObject(url, Monstre.class);
            int dollards = 0;
            int experience = 0;
            if(monstreAttaquant.getAttaque() * monstreAttaquant.getLevel() >= monstreDefense.getAttaque() * monstreDefense.getLevel() - 1){
                dollards = monstreDefense.getLevel() * 8 + monstreDefense.getAttaque();
                experience = monstreDefense.getAttaque() * 5;
            }
            return "{dollards:"+dollards+",expérience:"+experience+"}";
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
        return null;
    }
}
