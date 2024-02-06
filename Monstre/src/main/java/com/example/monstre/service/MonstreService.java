package com.example.monstre.service;

import com.example.monstre.model.Monstre;
import com.example.monstre.repository.MonstreRepository;
import com.example.monstre.rabbitmq.Envoyeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonstreService {

    @Autowired
    private MonstreRepository monstreRepository;

    @Autowired
    private Envoyeur envoyeur;

    public Monstre save(Monstre monstre) {
        Monstre newMonstre = monstreRepository.save(monstre);
        envoyeur.envoyer(newMonstre.toString());
        return newMonstre;
    }

    public void delete(int id) {
        monstreRepository.deleteById(id);
    }

    public List<Monstre> getAllMonstre() {
        return (List<Monstre>) monstreRepository.findAll();
    }
}
