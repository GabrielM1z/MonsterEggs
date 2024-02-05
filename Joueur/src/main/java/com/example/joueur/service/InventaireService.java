package com.example.joueur.service;

import com.example.joueur.model.Inventaire;
import com.example.joueur.repository.InventaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventaireService {

    @Autowired
    private InventaireRepository inventaireRepository;

    public Inventaire save(Inventaire inventaire){
        return inventaireRepository.save(inventaire);
    }

    public List<Inventaire> getAllInventaire() {
        return (List<Inventaire>) inventaireRepository.findAll();
    }

    public Inventaire getByType(String type) {
        return inventaireRepository.findByType(type);
    }

}
