package com.example.joueur.controller;

import com.example.joueur.model.Equipe;
import com.example.joueur.model.Inventaire;
import com.example.joueur.service.EquipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/equipe")
public class EquipeController {

    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Equipe> getAll() {
        // This returns a JSON or XML with the users
        return equipeService.getAllEquipe();
    }
}
