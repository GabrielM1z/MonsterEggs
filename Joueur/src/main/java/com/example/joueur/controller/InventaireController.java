package com.example.joueur.controller;

import com.example.joueur.model.Inventaire;
import com.example.joueur.service.InventaireService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/inventaire")
public class InventaireController {

    private final InventaireService inventaireService;

    public InventaireController(InventaireService inventaireService) {
        this.inventaireService = inventaireService;
    }


    /**
     * Route de vérification service up
     */
    @GetMapping(path="/main")
    public @ResponseBody String main(){
        return "OK";
    }



    /**
     * Route pour ajouter des items dans l'inventaire
     * (ajouter à un item déjà existant)
     * @param type le type d'item
     * @param qte la quantité de cet item
     * @return l'item ajouté
     */

    @GetMapping("/add/{type}/{qte}")
    public @ResponseBody Inventaire addItem(
            @PathVariable String type,
            @PathVariable int qte
    )
    {

        int qte_courante = 0;
        try {
            Inventaire inventaire = inventaireService.getByType(type);
            qte_courante = inventaire.getQuantity();

            int res = qte + qte_courante;
            inventaire.setQuantity(res);
            return inventaireService.save(inventaire);
        }catch (Exception e){
            return createItem(type,qte);
        }
    }

    @GetMapping("/remove/{type}/{qte}")
    public @ResponseBody Inventaire removeItem(
            @PathVariable String type,
            @PathVariable int qte
    )
    {

        // On recup l'item par son type
        Inventaire inventaire = inventaireService.getByType(type);

        // on recup sa quantité
        int qte_courante = inventaire.getQuantity();

        // on met à jour sa quantité
        int res = qte_courante - qte;

        inventaire.setQuantity(res);

        // on return l'item dans ses nouvelles quantité
        return inventaireService.save(inventaire);
    }


    /**
     * Route pour creer un item dans l'inventaire
     * (l'item n'existe pas encore)
     * @param type le type de cet item
     * @param qte la quantité de cet item
     * @return l'item créé
     */
    @GetMapping("/create/{type}/{qte}")
    public @ResponseBody Inventaire createItem(
            @PathVariable String type,
            @PathVariable int qte
    )
    {
        // On crée un nouvel item
        final Inventaire inventaire = new Inventaire();

        // on set ses caractéristique
        inventaire.setType(type);
        inventaire.setQuantity(qte);

        // On return l'item créé
        return inventaireService.save(inventaire);
    }


    /**
     * Route pour recup tout les item de l'inventaire
     * @return tout les items
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Inventaire> getAll() {
        return inventaireService.getAllInventaire();
    }


    /**
     * Route pour connaitre la quantité d'un certain item dans l'inventaire
     * @param type le type de l'item
     * @return la quantité de l'item
     */
    @GetMapping("/get/{type}")
    public @ResponseBody String getItem(@PathVariable String type)
    {
        Inventaire inventaire = inventaireService.getByType(type);
        int qte_courante = inventaire.getQuantity();
        return String.valueOf(qte_courante);
    }
}
