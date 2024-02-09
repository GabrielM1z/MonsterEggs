package com.example.api_gateway.web.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class gateway {

    // Attributs
    private final Map<String, Integer> liste = new HashMap<>();

    // Constructeur
    public gateway()
    {
        // liste des microservices
        liste.put("API_Gateway", 8080);
        liste.put("Boutique", 8081);
        liste.put("Cave", 8082);
        liste.put("Coffre", 8083);
        liste.put("Combat", 8084);
        liste.put("Joueur", 8085);
        liste.put("Monstre", 8086);
        liste.put("Log", 8087);

        // mettre de l'argent à la base
        String url = "http://localhost:8085/inventaire/create/dollards/100";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
    }

    /**
     * Route page d'accueil
     * @return
     */
    @GetMapping("/")
    public String index() {
        int valeur = liste.get("API_Gateway");
        String url = "http://localhost:" + valeur + "/front/index.html";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }



    ////////////////////////////////////////////////////////////
    //                     Route BOUTIQUE                     //
    ////////////////////////////////////////////////////////////


    /**
     * Route pour l'achat d'un item dans la boutique
     * (achat de : oeuf / incubateur)
     * @param itemId
     * @return
     */
    @GetMapping("/API/Boutique/BuyItem/{itemId}")
    private String BuyItem(@PathVariable String itemId) {
        // TODO gestion des erreurs sur les appels API des microservices

        // si la boutique est down
        if (!testBoutique()) {
            return "Boutique indisponible";
        }

        // si le joueur est down
        if (!testJoueur()) {
            return "Joueur indisponible";
        }

        // on recup les ports de chaque microservice utile
        int idBoutique = liste.get("Boutique");
        int idJoueur = liste.get("Joueur");
        int idCave = liste.get("Cave");

        // On recup le prix de l'item
        String urlGetPrice = "http://localhost:" + idBoutique + "/boutique/GetPrice/" + itemId;
        RestTemplate restGetPrice = new RestTemplate();
        int price = restGetPrice.getForObject(urlGetPrice, Integer.class);

        // On recup la quantité de l'item
        String urlGetQuantity = "http://localhost:" + idBoutique + "/boutique/GetQuantity/" + itemId;
        RestTemplate restGetQuantity = new RestTemplate();
        int quantity = restGetQuantity.getForObject(urlGetQuantity, Integer.class);

        // On recup le type de l'item
        String urlGetType = "http://localhost:" + idBoutique + "/boutique/GetType/" + itemId;
        RestTemplate restGetType = new RestTemplate();
        String type = restGetType.getForObject(urlGetType, String.class);

        // On recup notre quantité de dollards
        String urlGetDollards = "http://localhost:" + idJoueur + "/inventaire/get/dollards";
        RestTemplate restGetDollards = new RestTemplate();
        int dollards = restGetDollards.getForObject(urlGetDollards, Integer.class);

        // Si on a moins de dollards que le prix de l'item
        if (dollards < price) {
            return "Vous n'avez pas assez de dollards.";
        }
        // Sinon
        else {
            String urlAddItem = "";
            switch (type)
            {
                // Si l'item est un incubateur
                case "incubateur":

                    // on vérifie si la cave est down
                    if (!testCave())
                    {
                        return "Cave indisponible";
                    }

                    // On cherche à savoir si on peut acheter un nouveau incubateur
                    String urlGetNbIncubateur = "http://localhost:" + idCave + "/cave/isFreeIncubateur";
                    RestTemplate restGetNbIncubateur = new RestTemplate();
                    boolean isFreeIncubateur = restGetNbIncubateur.getForObject(urlGetNbIncubateur, Boolean.class);

                    if (!isFreeIncubateur){
                        return "Impossible d'acheter l'incubateur vous en avez atteint la limite";
                    }

                    // On l'ajoute à la cave
                    urlAddItem = "http://localhost:" + idCave + "/cave/add";
                    new RestTemplate().getForObject(urlAddItem, String.class);
                    break;

                // Dans le cas ou c'est autre chose
                case null:
                    break;

                // Si l'item est un oeuf
                default:
                    // On l'ajoute à l'inventaire
                    urlAddItem = "http://localhost:" + idJoueur + "/inventaire/add/" + type + "/" + quantity;
                    new RestTemplate().getForObject(urlAddItem, String.class);
                    break;
            }

            // On supprime nos dollards (autant que le prix de l'item
            String urlRemoveDollards = "http://localhost:" + idJoueur + "/inventaire/remove/dollards/" + price;
            new RestTemplate().getForObject(urlRemoveDollards, String.class);

            // On supprime l'item acheté de la boutique
            String urlRemoveItem = "http://localhost:" + idBoutique + "/boutique/delete/" + itemId;
            new RestTemplate().getForObject(urlRemoveItem, String.class);

            // On return ok
            return "ok";
        }
    }



    /**
     *  fonction qui permet de refresh la boutique à la demande du joueur (10$)
     * @return
     */
    @GetMapping("/API/Boutique/refresh")
    private String refresh() {
        int idJoueur = liste.get("Joueur");
        int idBoutique = liste.get("Boutique");

        // si la boutique est down
        if (!testBoutique())
        {
            return "Boutique indisponible";
        }

        // si la boutique est down
        if (!testJoueur())
        {
            return "Boutique indisponible";
        }

        // On recup notre quantité de dollards
        String urlGetDollards = "http://localhost:" + idJoueur + "/inventaire/get/dollards";
        RestTemplate restGetDollards = new RestTemplate();
        int dollards = restGetDollards.getForObject(urlGetDollards, Integer.class);

        // Si on a moins de dollards que 10 dollards
        if(dollards<10)
        {
            return  "Vous n'avez pas assez de dollards.";
        }
        // Sinon
        else {
            String urlRemoveDollards = "http://localhost:" + idJoueur + "/inventaire/remove/dollards/" + 10;
            new RestTemplate().getForObject(urlRemoveDollards, String.class);

            // On supprime l'item acheté de la boutique
            String urlRefresh = "http://localhost:" + idBoutique + "/boutique/refresh";
            new RestTemplate().getForObject(urlRefresh, String.class);
            return  "ok";
        }
    }


    /**
     *
     * @return
     */
    @GetMapping("/API/Boutique/Get")
    private String GetShop() {
        int valeur = liste.get("Boutique");
        String url = "http://localhost:" + valeur + "/boutique/all";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

//    @GetMapping("/API/Monstre/Create")
//    private String CreateMonstre() {
//        int valeur = liste.get("Monstre");
//        String url = "http://localhost:" + valeur + "/creation";
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject(url, String.class);
//    }

    @GetMapping("/API/Joueur/GetDollards")
    private String getDollards() {
        int valeur = liste.get("Joueur");
        String url = "http://localhost:" + valeur + "/inventaire/get/dollards";
        RestTemplate restTemplate = new RestTemplate();
        return   restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/API/Joueur/addOneDollards")
    private String addOneDollards() {
        int valeur = liste.get("Joueur");
        String url = "http://localhost:" + valeur + "/inventaire/add/dollards/1";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }


    @GetMapping("/API/Inventaire/Get")
    private String getInventaire() {
        int valeur = liste.get("Joueur");
        String url = "http://localhost:" + valeur + "/inventaire/all";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    ////////////////////////////////////////////////////////////
    //                  OPERATIONS MONSTRES                   //
    ////////////////////////////////////////////////////////////

    @GetMapping("/API/Equipe/Get")
    private String GetEquipe() {
        int valeur = liste.get("Joueur");
        String url = "http://localhost:" + valeur + "/equipe/all";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/API/Coffre/Get")
    private String GetCoffre() {
        int valeur = liste.get("Coffre");
        String url = "http://localhost:" + valeur + "/coffre/all";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * Route pour transférer un Monstre de l'Equipe vers le Coffre
     * @return
     */
    @GetMapping("/API/TransfertEquipeCoffre/{idMonstre}")
    private String transfertEquipeCoffre(@PathVariable int idMonstre)
    {
        // recup les ports des micro-services
        int idJoueur = liste.get("Joueur");
        int idCoffre = liste.get("Coffre");

        // test si les micro-services sont up
        if (!testJoueur())
        {
            return "Joueur indisponible (Equipe et Inventaire)";
        }
        if (!testCoffre())
        {
            return "Coffre indisponible";
        }

        // Recup les info du monstres à déplacer
        String urlGetMonstre = "http://localhost:" + idJoueur + "/equipe/get/" + idMonstre;
        RestTemplate restGetMonstre = new RestTemplate();
        String nomMonstre = restGetMonstre.getForObject(urlGetMonstre, String.class);

        // Ajouter monstres au coffre
        String urlAddMonstreCoffre = "http://localhost:" + idCoffre + "/add/" + idMonstre + "/" + nomMonstre;
        new RestTemplate().getForObject(urlAddMonstreCoffre, String.class);

        // Supprimer monstre de l'équipe
        String urlDeleteMonstreEquipe = "http://localhost:" + idJoueur + "/equipe/remove/" + idMonstre;
        new RestTemplate().getForObject(urlDeleteMonstreEquipe, String.class);

        // return message sucess
        return "Tranfert du monstre (" + idMonstre + " : " + nomMonstre + ") réussi";
    }


    /**
     * Route pour relacher un Monstre depuis l'Equipe
     * @return
     */
    @GetMapping("/API/RelacherMonstreEquipe/{idMonstre}")
    private String relacherMonstreEquipe(@PathVariable int idMonstre)
    {
        // recup les ports des micro-services
        int idJoueur = liste.get("Joueur");

        // test si les micro-services sont up
        if (!testJoueur())
        {
            return "Joueur indisponible (Equipe et Inventaire)";
        }

        // Recup les info du monstres à déplacer
        String urlGetMonstre = "http://localhost:" + idJoueur + "/equipe/get/" + idMonstre;
        RestTemplate restGetMonstre = new RestTemplate();
        String nomMonstre = restGetMonstre.getForObject(urlGetMonstre, String.class);

        // Supprimer monstre de l'équipe
        String urlDeleteMonstreEquipe = "http://localhost:" + idJoueur + "/equipe/remove/" + idMonstre;
        new RestTemplate().getForObject(urlDeleteMonstreEquipe, String.class);

        // return message sucess
        return "Le Monstre (" + idMonstre + " : " + nomMonstre + ") à été relaché";
    }

    @GetMapping("/API/CombatMonstreEquipe/{idMonstre}/{difficulte}")
    private String CombatMonstreEquipe(@PathVariable int idMonstre, @PathVariable int difficulte)
    {
        int idCombat = liste.get("Combat");
        int idJoueur = liste.get("Joueur");
        int valeurMonstre = liste.get("Monstre");

        // test si les micro-services sont up
        if (!testCombat())
        {
            return "Combat indisponible";
        }

        String urlCombat= "http://localhost:" + idCombat + "/combat/" + idMonstre + "/" + difficulte;
        String rep = new RestTemplate().getForObject(urlCombat, String.class);

        JSONObject jsonObject = new JSONObject(rep);

        String strReturn = "";

        if(jsonObject.getInt("oeuf") != 0){
            if (testJoueur())
            {
                // On l'ajoute à l'inventaire
                String urlAddItem = "http://localhost:" + idJoueur + "/inventaire/add/oeuf/" + jsonObject.getInt("oeuf");
                new RestTemplate().getForObject(urlAddItem, String.class);
                strReturn = strReturn + jsonObject.getInt("oeuf") + " oeuf gagné !\n";
            }
        }
        if(jsonObject.getInt("experience") != 0){
            if (testMonstre()){
                //On ajoute l'expérience
                String urlAddItem = "http://localhost:" + valeurMonstre + "/monstre/xp/"+idMonstre+"/" + jsonObject.getInt("experience");
                String retour = new RestTemplate().getForObject(urlAddItem, String.class);
                strReturn = strReturn + retour + "\n";
            }
        }
        if(jsonObject.getInt("dollards") != 0){
            if (testJoueur()){
                // On l'ajoute à l'inventaire
                String urlAddItem = "http://localhost:" + idJoueur + "/inventaire/add/dollards/" + jsonObject.getInt("dollards");
                new RestTemplate().getForObject(urlAddItem, String.class);
                strReturn = strReturn + jsonObject.getInt("dollards") + " dollards gagné !";
            }
        }
        return strReturn;
    }


    ////////////////////////////////////////////////////////////
    //                    OPERATIONS OEUFS                    //
    ////////////////////////////////////////////////////////////

    /**
     * Route pour vendre un oeuf
     * @return
     */
    @GetMapping("/API/VendreOeuf")
    private String vendreOeuf()
    {
        // recup les ports des micro-service
        int idJoueur = liste.get("Joueur");

        // test si les micro-services sont up
        if (!testJoueur())
        {
            return "Joueur indisponible";
        }


        // On recup notre quantité de dollards
        String urlGetOeufs = "http://localhost:" + idJoueur + "/inventaire/get/oeufs";
        RestTemplate restGetOeufs = new RestTemplate();
        int nbOeufs = restGetOeufs.getForObject(urlGetOeufs, Integer.class);

        // Si on a moins de dollards que 10 dollards
        if(nbOeufs<1)
        {
            return  "";
        }
        // Sinon
        else {

            // supprimer 1 oeuf
            String urlDelete1Oeuf = "http://localhost:" + idJoueur + "/inventaire/remove/oeufs/1";
            new RestTemplate().getForObject(urlDelete1Oeuf, String.class);

            // ajouter 10 dollards au portemonnaie
            String urlAjouter10Dollards = "http://localhost:" + idJoueur + "/inventaire/add/dollards/10";
            new RestTemplate().getForObject(urlAjouter10Dollards, String.class);

            // return message sucess
            return "1 Oeuf a bien été vendu, vous avez gagné 10 dollards ";
        }
    }


    /**
     * Route pour vendre un oeuf
     * @return
     */
    @GetMapping("/API/IncuberOeuf")
    private String incuberOeuf()
    {
        // recup les ports des micro-service
        int idJoueur = liste.get("Joueur");
        int idCave = liste.get("Cave");
        // test si les micro-services sont up
        if (!testJoueur())
        {
            return "Joueur indisponible";
        }
        if(!testCave())
        {
            return "Cave indisponible";
        }


        // On recup notre quantité d'oeufs
        String urlGetOeufs = "http://localhost:" + idJoueur + "/inventaire/get/oeufs";
        RestTemplate restGetOeufs = new RestTemplate();
        int nbOeufs = restGetOeufs.getForObject(urlGetOeufs, Integer.class);

        String urlCheckIncubateurEmpty = "http://localhost:" + idCave + "/cave/checkVide";
        boolean checkVide = new RestTemplate().getForObject(urlCheckIncubateurEmpty, boolean.class);
        // Si on a des oeufs
        if(nbOeufs<1 || !checkVide)
        {
            return  nbOeufs + " " + checkVide;
        }
        // Sinon
        else {
            // supprimer 1 oeuf
            String urlDelete1Oeuf = "http://localhost:" + idJoueur + "/inventaire/remove/oeufs/1";
            new RestTemplate().getForObject(urlDelete1Oeuf, String.class);

            String urlAddOeuf = "http://localhost:" + idCave + "/cave/addOeuf";
            new RestTemplate().getForObject(urlAddOeuf, String.class);

            // return message sucess
            return "1 Oeuf a bien été incuber !";
        }
    }




    ////////////////////////////////////////////////////////////
    //            Route de vérification services up           //
    ////////////////////////////////////////////////////////////


    @GetMapping("/APIGateway")
    public Boolean testAPI_GATEWAY() {
        return true;
    }

    @GetMapping("/Boutique")
    public Boolean testBoutique() {
        int valeur = liste.get("Boutique");
        String url = "http://localhost:" + valeur + "/boutique/main";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @GetMapping("/Cave")
    public Boolean testCave() {
        int valeur = liste.get("Cave");
        String url = "http://localhost:" + valeur + "/cave/main";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @GetMapping("/Coffre")
    public Boolean testCoffre() {
        int valeur = liste.get("Coffre");
        String url = "http://localhost:" + valeur + "/coffre/main";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @GetMapping("/Combat")
    public Boolean testCombat() {
        int valeur = liste.get("Combat");
        String url = "http://localhost:" + valeur + "/combat/main";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @GetMapping("/Joueur")
    public Boolean testJoueur() {
        int valeur = liste.get("Joueur");
        String url = "http://localhost:" + valeur + "/inventaire/main";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @GetMapping("/Log")
    public Boolean testLog() {
        int valeur = liste.get("Log");
        String url = "http://localhost:" + valeur + "/log/main";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @GetMapping("/Monstre")
    public Boolean testMonstre() {
        int valeur = liste.get("Monstre");
        String url = "http://localhost:" + valeur + "/monstre/main";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}