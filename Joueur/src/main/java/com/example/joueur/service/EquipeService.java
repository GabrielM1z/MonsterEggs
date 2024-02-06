package com.example.joueur.service;

import com.example.joueur.model.MonstreEquipe;
import com.example.joueur.repository.MonstreEquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {

    @Autowired
    private MonstreEquipeRepository monstreEquipeRepository;

    final private int maxMonstres = 6;

    public MonstreEquipe save(MonstreEquipe monstreEquipe){
        return monstreEquipeRepository.save(monstreEquipe);
    }

    public List<MonstreEquipe> getAllEquipe() {
        return (List<MonstreEquipe>) monstreEquipeRepository.findAll();
    }

    public int getMaxMonstres() { return maxMonstres; }

    public void delete(int id)
    {
        monstreEquipeRepository.deleteById(id);
    }

    public MonstreEquipe get(int id){
        Optional<MonstreEquipe> monstreEquipe =  monstreEquipeRepository.findById(id);
        return monstreEquipe.orElse(null);
    }
}
