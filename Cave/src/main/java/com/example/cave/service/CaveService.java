package com.example.cave.service;

import com.example.cave.model.Incubateur;
import com.example.cave.repository.IncubateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
