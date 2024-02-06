package com.example.cave.controller;

import com.example.cave.model.Incubateur;
import com.example.cave.service.CaveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
     * Route pour supprimer un incubateur avec son id
     * @param id id de l'incubateur
     */
    @GetMapping(path="/suppression/{id}")
    public @ResponseBody void suppressionIncubateur(@PathVariable int id)
    {
        caveService.delete(id);
    }


    /**
     * Route pour savoir combien d'incubateur on a
     * @return nb d'incubateur
     */
    @GetMapping(path="/nbIncubateur")
    public @ResponseBody int getNbIncubateur()
    {
        return caveService.getAllIncubateur().size();
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
     *
     * @param id
     * @return
     */
    @GetMapping(path="/checkVide/{id}")
    public @ResponseBody boolean checkVide(@PathVariable int id)
    {
        // on recup l'incubateur
        Incubateur incubateur = caveService.getIncubateurById(id);

        // return true si vide, false si oeuf
        return !incubateur.hasOeuf();
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

        // On génère un nombre aléatoire entre 1 et 10 pour le temps d'éclosion en secondes * 10
        Random random = new Random();
        int tempsRand = random.nextInt(10)*10 + 1;
        incubateur.setDateEclosion(LocalDateTime.now().plusSeconds(tempsRand));
        // on set l'oeuf à true
        incubateur.setOeuf(true);

        // on return l'incubateur modifié
        return caveService.save(incubateur);
    }
}