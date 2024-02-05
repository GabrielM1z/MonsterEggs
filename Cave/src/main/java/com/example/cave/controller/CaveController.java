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
    public @ResponseBody Iterable<Incubateur> getAll() {
        // This returns a JSON or XML with the users
        return caveService.getAllIncubateur();
    }

    /**
     * Route pour ajouter un incubateur dans la cave
     * @return l'incubateur ajouté
     */
    @GetMapping(path="/add")
    public @ResponseBody Incubateur addIncubateur()
    {
        final Incubateur incubateur = new Incubateur();

        Random rand = new Random();
        float tempsRand = 1 + rand.nextFloat() * (10 - 1);

        incubateur.setOeuf(false);
        incubateur.setTemps(0);

        return caveService.save(incubateur);
    }

    /**
     * Route pour supprimer un incubateur avec son id
     * @param id id de l'incubateur
     */
    @GetMapping(path="/suppression/{id}")
    public @ResponseBody void suppressionIncubateur(
            @PathVariable int id
    )
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
    public @ResponseBody boolean checkVide(
            @PathVariable int id
    )
    {
        Incubateur incubateur = caveService.getIncubateurById(id);
        return !incubateur.isOeuf();
    }

    @GetMapping(path="/addOeuf/{idIncubateur}")
    public @ResponseBody Incubateur ajouterOeufIncubateur(
            @PathVariable int idIncubateur
    )
    {
        Incubateur incubateur = caveService.getIncubateurById(idIncubateur);

        incubateur.setOeuf(true);

        Random rand = new Random();
        float tempsRand = 1 + rand.nextFloat() * (10 - 1);
        incubateur.setTemps(tempsRand);

        return caveService.save(incubateur);
    }


}