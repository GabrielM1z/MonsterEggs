package com.example.boutique.controller;

import com.example.boutique.model.Boutique;
import com.example.boutique.service.BoutiqueService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;

@Controller
@RequestMapping(path="/boutique")
public class BoutiqueController {

    private final BoutiqueService boutiqueService;

    // liste des objets achetable
    private final String[] nomItemList = {
            "incubateur",
            "oeufs"
    };

    public BoutiqueController(BoutiqueService boutiqueService) {
        this.boutiqueService = boutiqueService;
    }

    /**
     * Route de vérification service up
     */
    @GetMapping(path="/main")
    public @ResponseBody String main(){
        return "OK";
    }

    /**
     * Route de rafraichissement de la boutique
     */
    @GetMapping(path="/refresh")
    @Scheduled(fixedRate = 10000)
    private @ResponseBody void Refresh() {

        // delete de tous les items de la boutique
        boutiqueService.deleteAll();

        // declaration de l'objet random
        Random rand = new Random();

        // creation de 4 objet dans la boutique
        for (int i = 0; i < 4; i++) {

            // new item dans la boutique
            Boutique item = new Boutique();

            //// on set ses caracteristique
            // nom
            String nom = nomItemList[rand.nextInt(nomItemList.length)];
            item.setNom(nom);
            // prix
            int price = rand.nextInt(31) + 20;
            item.setPrice(price);
            // quantité
            //int qte = rand.nextInt(10 - 1 + 1) + 1;
            int qte = 1;
            item.setQuantity(qte);

            // on save l'item en BDD
            boutiqueService.save(item);
        }
    }

    /**
     * Route pour récupérer tout les item de la boutique
     * @return
     */
    @GetMapping("/all")
    public @ResponseBody Iterable<Boutique> getAll() {
        return boutiqueService.getAllBoutique();
    }


    /**
     * Route pour supprimer un item de la boutique par son id
     * @param itemId
     */
    @GetMapping("/delete/{itemId}")
    private @ResponseBody void deleteItem(@PathVariable int itemId) {
        boutiqueService.deleteById(itemId);
    }


    /**
     * Route pour connaitre le prix d'un item de la boutique
     * @param itemId id de l'item
     */
    @GetMapping("/GetPrice/{itemId}")
    public @ResponseBody int getPrice(@PathVariable int itemId)
    {
        Boutique item = boutiqueService.getItem(itemId);
        return item.getPrice();
    }

    /**
     * Get le type/nom d'un item (oeuf, dollards, ...)
     */
    @GetMapping("/GetType/{itemId}")
    public @ResponseBody String getNom(@PathVariable int itemId)
    {
        Boutique item = boutiqueService.getItem(itemId);
        return item.getNom();
    }

    /**
     * Get la quantité d'un item
     */
    @GetMapping("/GetQuantity/{itemId}")
    public @ResponseBody int getQuantity(@PathVariable int itemId)
    {
        Boutique item = boutiqueService.getItem(itemId);
        return item.getQuantity();
    }
}
