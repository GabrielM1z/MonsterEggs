package com.example.monstre.controller;

import com.example.monstre.service.MonstreService;
import com.example.monstre.model.Monstre;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;


@Controller
@RequestMapping(path="/monstre")
public class MonstreController {
    private final MonstreService monstreService;
    private final ArrayList<String> nomMonstreList = new ArrayList<>();

    public MonstreController(MonstreService monstreService) {
        this.monstreService = monstreService;

        // liste des nom de monstres
        this.nomMonstreList.add("DamienTg");
        this.nomMonstreList.add("GabBravo");
        this.nomMonstreList.add("MaxOuTonPere");
        this.nomMonstreList.add("Selamouche");
        this.nomMonstreList.add("Tentacouille");
        this.nomMonstreList.add("QuoiCouBERRRRRHH");
    }

    /**
     * Route de vérification service up
     */
    @GetMapping(path="/main")
    public @ResponseBody String main(){
        return "OK";
    }

    /**
     * Route de création aléatoire de monstres
     */
    @GetMapping(path="/creation")
    public @ResponseBody Monstre creationMonstre()
    {
        final Monstre monstre = new Monstre();

        Random rand = new Random();
        String nom = nomMonstreList.get(rand.nextInt(nomMonstreList.size()));
        monstre.setNom(nom);
        monstre.setAttaque(rand.nextInt(10));
        monstre.setLevel(1);
        monstre.setXp(0);
        return monstreService.save(monstre);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Monstre> getAll() {
        // This returns a JSON or XML with the users
        return monstreService.getAllMonstre();
    }

    /**
     * Route de récupération d'un monstre
     * @param id du monstre qui va etre récupérer
     */
    @GetMapping(path="/get/{id}")
    public @ResponseBody Optional<Monstre> getById(
            @PathVariable int id
    )
    {
        return monstreService.getById(id);
    }

    @GetMapping(path="/xp/{id}/{xp}")
    public @ResponseBody void getById(
            @PathVariable int xp,
            @PathVariable int id
    )
    {
        monstreService.upXP(id, xp);
    }

    @GetMapping(path="/getRandomMonstre")
    public @ResponseBody Monstre getRandomMonstre(
    )
    {
        return monstreService.getRandomMonstre();
    }
}

