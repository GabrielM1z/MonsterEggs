package com.example.cave.controller;

import com.example.cave.model.Incubateur;
import com.example.cave.service.CaveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;
import java.util.Random;
import java.time.LocalDateTime;

@Controller
@RequestMapping(path="/cave")
public class CaveController
{

    private final CaveService caveService;

    public CaveController(CaveService caveService) {
        this.caveService = caveService;
    }


    /**
     * Route de vérification service up
     */
    @GetMapping(path="/main")
    public @ResponseBody String main(){
        return "OK";
    }

    /**
     * Route pour recup tout les incubateurs
     * @return liste d'incubateur
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Incubateur> getAll()
    {
        return caveService.getAllIncubateur();
    }


    /**
     * Route pour ajouter un incubateur dans la cave
     * @return l'incubateur ajouté
     */
    @GetMapping(path="/add")
    public @ResponseBody Incubateur addIncubateur()
    {
        // creation de l'incubateur
        final Incubateur incubateur = new Incubateur();

        // on set les info de l'incubateur vide
        incubateur.resetOeuf();
        // on return l'incubateur créé
        return caveService.save(incubateur);
    }

    /**
     * Route pour savoir si on peut ajouter un incubateur dans la cave
     * @return nb d'incubateur < maxIncutabeur
     */
    @GetMapping(path="/isFreeIncubateur")
    public @ResponseBody boolean isFreeIncubateur()
    {
        return caveService.isFreeIncubateur();
    }

    /**
     * Route pour savoir le nombre d'incubateur dans la cave
     * @return nb d'incubateur
     */
    @GetMapping(path="/getListId")
    public @ResponseBody List<Integer> getListId()
    {
        return caveService.getListId();
    }


    /**
     *
     * return true si vide, false si oeuf
     * @return
     */
    @GetMapping(path="/checkVide")
    public @ResponseBody boolean checkVide()
    {
        return caveService.checkVide();
    }


    /**
     * Route pour ajouter un oeuf à un incubateur
     *
     * @return
     */
    @GetMapping(path="/addOeuf")
    public @ResponseBody Incubateur ajouterOeufIncubateur()
    {
        // on return l'incubateur modifié
        return caveService.ajouterOeuf();
    }
}