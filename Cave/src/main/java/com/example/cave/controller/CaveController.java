package com.example.cave.controller;

import com.example.cave.model.Incubateur;
import com.example.cave.service.CaveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping(path="/cave")
public class CaveController
{

    private final CaveService caveService;

    public CaveController(CaveService caveService) {
        this.caveService = caveService;
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
        incubateur.setOeuf(false);
        incubateur.setTemps(0);

        // on return l'incubateur créé
        return caveService.save(incubateur);
    }


    /**
     * Route pour supprimer un incubateur avec son id
     * @param id id de l'incubateur
     */
    @GetMapping(path="/suppression/{id}")
    public @ResponseBody void suppressionIncubateur(@PathVariable int id)
    {
        caveService.delete(id);
    }


    /**
     * Route pour compter le nombre d'incubateur dans la cave
     * @return nb d'incubateur
     */
    @GetMapping(path="/compter")
    public @ResponseBody int compterIncubateur()
    {
        return caveService.getAllIncubateur().size();
    }

    @GetMapping(path="/checkVide/{id}")
    public @ResponseBody boolean checkVide(@PathVariable int id)
    {
        // on recup l'incubateur
        Incubateur incubateur = caveService.getIncubateurById(id);

        // return true si vide, false si oeuf
        return !incubateur.isOeuf();
    }


    /**
     * Route pour ajouter un oeuf à un incubateur
     * @param idIncubateur
     * @return
     */
    @GetMapping(path="/addOeuf/{idIncubateur}")
    public @ResponseBody Incubateur ajouterOeufIncubateur(@PathVariable int idIncubateur)
    {
        // on recup l'incubateur grace à l'id
        Incubateur incubateur = caveService.getIncubateurById(idIncubateur);

        // on set l'oeuf à true
        incubateur.setOeuf(true);

        // on set un temps d'éclosion aléatoire
        Random rand = new Random();
        float tempsRand = 1 + rand.nextFloat() * (10 - 1);
        incubateur.setTemps(tempsRand);

        // on return l'incubateur modifié
        return caveService.save(incubateur);
    }
}