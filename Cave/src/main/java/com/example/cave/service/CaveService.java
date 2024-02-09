package com.example.cave.service;

import com.example.cave.model.Incubateur;
import com.example.cave.repository.IncubateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CaveService {

    private final int maxIncubateur = 6;

    @Autowired
    private IncubateurRepository incubateurRepository;

    public Incubateur save(Incubateur incubateur){
        return incubateurRepository.save(incubateur);
    }

    public List<Incubateur> getAllIncubateur() {
        return (List<Incubateur>) incubateurRepository.findAll();
    }

    public Incubateur getIncubateurById(int id)
    {
        Optional<Incubateur> incubateur =  incubateurRepository.findById(id);
        return incubateur.orElse(null);
    }

    public boolean  isFreeIncubateur()
    {
        return getAllIncubateur().size() < getMaxIncubateur();
    }
    public int getMaxIncubateur() {
        return maxIncubateur;
    }

    public List<Integer>  getListId(){
        List<Integer> listId = null;
        for(Incubateur incubateur : getAllIncubateur() ){
            listId.add(incubateur.getId());
        }
        return listId;
    };

    public boolean checkVide(){
        List<Incubateur> listIncubateur = getAllIncubateur();
        for (Incubateur incubateur : listIncubateur){
            if(!incubateur.hasOeuf()){
                return true;
            }
        }
        return false;
    }

    public @ResponseBody Incubateur ajouterOeuf()
    {
        for(Incubateur incubateur : getAllIncubateur()) {

            if(!incubateur.hasOeuf()){
                // On génère un nombre aléatoire entre 1 et 10 pour le temps d'éclosion en secondes * 10
                Random random = new Random();
                int tempsRand = random.nextInt(10) * 10 + 1;
                incubateur.setDateEclosion(LocalDateTime.now().plusSeconds(tempsRand));
                // on set l'oeuf à true
                incubateur.setOeuf(true);

                // on return l'incubateur modifié
                return save(incubateur);
            }
        }
        return null;
    }
    @Scheduled(fixedRate = 1000)
    public void checkEclosion(){
        List<Incubateur> listIncubateur = getAllIncubateur();
        for (Incubateur incubateur : listIncubateur) {
            if(incubateur.hasOeuf()){
                if(LocalDateTime.now().isAfter(incubateur.getDateEclosion())){
                    String url = "http://localhost:8086/monstre/creation";
                    RestTemplate restTemplate = new RestTemplate();
                    try {
                        restTemplate.getForObject(url, String.class);
                        incubateur.resetOeuf();
                        save(incubateur);
                    } catch (Exception e) {
                        System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
                    }
                }
            }
        }
    }
}
