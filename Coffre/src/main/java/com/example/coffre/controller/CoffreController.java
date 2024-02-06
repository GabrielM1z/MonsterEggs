package com.example.coffre.controller;

import com.example.coffre.model.MonstreCoffre;
import com.example.coffre.service.CoffreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/coffre")
public class CoffreController {

    private final CoffreService coffreService;

    public CoffreController(CoffreService coffreService) {
        this.coffreService = coffreService;
    }

    /**
     * Route de vérification service up
     */
    @GetMapping(path="/main")
    public @ResponseBody String main(){
        return "OK";
    }

    /**
     * Route pour recuperer tout les monstres dans le coffre
     * @return tout les monstres
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<MonstreCoffre> getAll()
    {
        return coffreService.getAllMonstreCoffre();
    }


    /**
     * Ajoute un monstre dans le coffre
     * @param id id du monstre
     * @param nom nom du monstre
     * @return le monstre ajouté
     */
    @GetMapping(path="/add/{id}/{nom}")
    public @ResponseBody MonstreCoffre ajouterMonstre(
            @PathVariable int id,
            @PathVariable String nom
    )
    {
        final MonstreCoffre monstreCoffre = new MonstreCoffre();

        monstreCoffre.setId(id);
        monstreCoffre.setNom(nom);

        return coffreService.save(monstreCoffre);
    }


    /**
     * Supprimer un monstre du coffre
     * @param id
     */
    @GetMapping(path="/remove/{id}")
    public @ResponseBody void supprimerMonstre(@PathVariable int id)
    {
        coffreService.delete(id);
    }
}
