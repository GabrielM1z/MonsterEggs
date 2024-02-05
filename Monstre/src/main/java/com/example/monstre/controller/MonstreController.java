package com.example.monstre.controller;

import com.example.monstre.MonstreService;
import com.example.monstre.model.Monstre;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="/monstre")
public class MonstreController {
    private final MonstreService monstreService;

    public MonstreController(MonstreService monstreService) {
        this.monstreService = monstreService;
    }

    @GetMapping(path="/add/{nom}/{attaque}/{level}/{xp}") // Map ONLY GET Requests
    public @ResponseBody Monstre addNewMonstreGet(
            @PathVariable String nom,
            @PathVariable int attaque,
            @PathVariable int level,
            @PathVariable int xp
    ){
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        final Monstre monstre = new Monstre();
        monstre.setNom(nom);
        monstre.setAttaque(attaque);
        monstre.setLevel(level);
        monstre.setLevel(xp);
        return monstreService.save(monstre);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Monstre> getAll() {
        // This returns a JSON or XML with the users
        return monstreService.getAllMonstre();
    }
}

