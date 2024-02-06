package com.example.combat.service;

import com.example.combat.model.Monstre;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class CombatService {

    public String combat(Monstre monstreAttaquant, int difficulter)
    {
        String url = "http://localhost:8086/monstre/getRandomMonstre";
        RestTemplate restTemplate = new RestTemplate();
        try {
            Monstre monstreDefense = restTemplate.getForObject(url, Monstre.class);
            int dollards = 0;
            int experience = 0;
            int oeuf = 0;
            if(monstreAttaquant.getAttaque() * monstreAttaquant.getLevel() >= difficulter/2 * monstreDefense.getAttaque() * monstreDefense.getLevel() - 1){
                dollards = (monstreDefense.getLevel() * 8 + monstreDefense.getAttaque()) * difficulter/2;
                experience = monstreDefense.getAttaque() * 5 * difficulter/2;
                Random rand = new Random();
                if(rand.nextInt(10) % 5 == 0){
                    oeuf = 1;
                }
            }
            return "{dollards:"+dollards+",expérience:"+experience+",oeuf:"+oeuf+"}";
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
        return null;
    }
}
