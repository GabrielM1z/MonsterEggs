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
        Inventaire inventaire = inventaireService.getByType(type);
        int qte_courante = inventaire.getQuantity();
        int res = qte_courante - qte;
        inventaire.setQuantity(res);
        return inventaireService.save(inventaire);
    }

    @GetMapping("/create/{type}/{qte}")
    public @ResponseBody Inventaire createItem(
            @PathVariable String type,
            @PathVariable int qte
    )
    {
        final Inventaire inventaire = new Inventaire();

        inventaire.setType(type);
        inventaire.setQuantity(qte);

        return inventaireService.save(inventaire);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Inventaire> getAll() {
        // This returns a JSON or XML with the users
        return inventaireService.getAllInventaire();
    }

    @GetMapping("/get/{type}")
    public @ResponseBody String getItem(@PathVariable String type){
        Inventaire inventaire = inventaireService.getByType(type);
        int qte_courante = inventaire.getQuantity();
        return String.valueOf(qte_courante);
    }
}
