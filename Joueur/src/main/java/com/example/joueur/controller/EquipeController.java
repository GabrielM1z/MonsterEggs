package com.example.joueur.controller;

import com.example.joueur.model.MonstreEquipe;
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


    /**
     * Route pour recuperer tout les monstres dans l'équipe
     * @return tout les monstres
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<MonstreEquipe> getAll()
    {
        return equipeService.getAllEquipe();
    }


    /**
     * Ajoute un monstre dans l'équipe
     * @param id id du monstre
     * @param nom nom du monstre
     * @return le monstre ajouté
     */
    @GetMapping(path="/add/{id}/{nom}")
    public @ResponseBody MonstreEquipe ajouterMonstre(
            @PathVariable int id,
            @PathVariable String nom
    )
    {
        final MonstreEquipe monstreEquipe = new MonstreEquipe();

        monstreEquipe.setId(id);
        monstreEquipe.setNom(nom);

        return equipeService.save(monstreEquipe);
    }


    /**
     * Supprimer un monstre de l'équipe
     * @param id
     */
    @GetMapping(path="/remove/{id}")
    public @ResponseBody void supprimerMonstre(@PathVariable int id)
    {
        equipeService.delete(id);
    }
}
