package com.example.joueur.service;

import com.example.joueur.model.Equipe;
import com.example.joueur.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    final private int maxMonstres = 6;

    public Equipe save(Equipe equipe){
        return equipeRepository.save(equipe);
    }

    public List<Equipe> getAllEquipe() {
        return (List<Equipe>) equipeRepository.findAll();
    }

    public int getMaxMonstres() { return maxMonstres; }
}
