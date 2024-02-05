package com.example.boutique.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@RestController
@CrossOrigin
public class BoutiqueController {
    @GetMapping("/main")
    public String main(){
        return "Hello Boutique !";
    }

    @GetMapping("/CreateItem")
    private String CreateItem() {
        String[] itemNames = {"Article 1", "Article 2", "Article 3", "Article 4"};
        double[] priceRanges = {10.0, 20.0}; // Plage de prix
        int[] quantityRanges = {5, 10}; // Plage de quantité

        StringBuilder response = new StringBuilder();
        response.append("Liste des articles générés : \n");

        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(itemNames.length);
            String itemName = itemNames[randomIndex];

            int randomPrice = random.nextInt(31) + 20; // Le prix sera entre 20 et 50 inclusivement

            int minQuantity = quantityRanges[0];
            int maxQuantity = quantityRanges[1];
            int randomQuantity = random.nextInt(maxQuantity - minQuantity + 1) + minQuantity;

            response.append("Nom : ").append(itemName).append(", Prix : ").append(randomPrice).append(", Quantité : ").append(randomQuantity).append("\n");
        }

        return response.toString();
    }

    @GetMapping("/BuyItem/{itemId}")
    private String BuyItem(@PathVariable String itemId) {
        //String response = "Vous avez acheté l'item avec l'IDé : " + itemId;
        String response = getPrice(itemId);

        return response;
    }

    @GetMapping("/GetPrice/{itemId}")
    public String getPrice(@PathVariable String itemId) {
        String itemName = getItemNameFromDatabase(itemId);
        double itemPrice = getItemPriceFromDatabase(itemId);
        int itemQuantity = getItemQuantityFromDatabase(itemId);

        String response = "L'article " + itemName + " coûte " + itemPrice + " euros et il reste " + itemQuantity + " en stock.";
        return response;
    }

    private String getItemNameFromDatabase(String itemId) {
       String[] itemNames = {"Article 1", "Article 2", "Article 3", "Article 4"};
        return itemNames[Integer.parseInt(itemId) - 1];
    }

    private double getItemPriceFromDatabase(String itemId) {
        double[] itemPrices = {10.99, 19.99, 7.99, 20.00};
        return itemPrices[Integer.parseInt(itemId) - 1];
    }

    private int getItemQuantityFromDatabase(String itemId) {
        int[] itemQuantities = {50, 100, 25, 5};
        return itemQuantities[Integer.parseInt(itemId) - 1];
    }
}
