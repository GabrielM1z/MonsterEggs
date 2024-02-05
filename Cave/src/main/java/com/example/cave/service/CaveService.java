package com.example.cave.service;

import com.example.cave.model.Incubateur;
import com.example.cave.repository.IncubateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaveService {

    @Autowired
    private IncubateurRepository incubateurRepository;

    public Incubateur save(Incubateur incubateur){
        return incubateurRepository.save(incubateur);
    }

    public void delete(int id){
        incubateurRepository.deleteById(id);
    }

    public List<Incubateur> getAllIncubateur() {
        return (List<Incubateur>) incubateurRepository.findAll();
    }

    public Incubateur getIncubateurById(int id)
    {
        Optional<Incubateur> incubateur =  incubateurRepository.findById(id);
        return incubateur.orElse(null);
    }
}
