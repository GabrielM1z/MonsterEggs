package com.example.monstre.controller;

import com.example.monstre.service.MonstreService;
import com.example.monstre.model.Monstre;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
     * Route de création personnalisé du monstre
     * @param nom nom du monstre
     * @param attaque attaque du monstre
     * @param level niveau du monstre
     * @param xp experience du monstre
     * @return monstre créé
     */
    @GetMapping(path="/add/{nom}/{attaque}/{level}/{xp}") // Map ONLY GET Requests
    public @ResponseBody Monstre addNewMonstreGet(
            @PathVariable String nom,
            @PathVariable int attaque,
            @PathVariable int level,
            @PathVariable int xp
    ){
        final Monstre monstre = new Monstre();

        monstre.setNom(nom);
        monstre.setAttaque(attaque);
        monstre.setLevel(level);
        monstre.setXp(xp);

        return monstreService.save(monstre);
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
        monstre.setAttaque(1);
        monstre.setLevel(1);
        monstre.setXp(0);
        return monstreService.save(monstre);
    }

    /**
     * Route de suppression de monstres
     * @param id du monstre à supprimer
     */
    @GetMapping(path="/suppression/{id}")
    public @ResponseBody void suppressionMonstre(
            @PathVariable int id
    )
    {
        monstreService.delete(id);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Monstre> getAll() {
        // This returns a JSON or XML with the users
        return monstreService.getAllMonstre();
    }
}

