package com.example.monstre.service;

import com.example.monstre.model.Monstre;
import com.example.monstre.repository.MonstreRepository;
import com.example.monstre.rabbitmq.Envoyeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    public List<Monstre> getAllMonstre() {
        return (List<Monstre>) monstreRepository.findAll();
    }

    public Optional<Monstre> getById(int id){
        return monstreRepository.findById(id);
    }

    public Monstre getRandomMonstre()
    {
        List<Monstre> listMonstre = (List<Monstre>) monstreRepository.findAll();
        Random rand = new Random();
        return listMonstre.get(rand.nextInt(listMonstre.size()));
    }

    public String upXP(int id, int xp)
    {
        Monstre monstre = getById(id).get();
        int xpTotal = monstre.getXp() + xp;
        int nbLevel = 0;
        while(xpTotal >= 100){
            nbLevel = nbLevel + 1;
            monstre.setLevel(monstre.getLevel()+1);
            xpTotal = xpTotal - 100;
        }
        String strLevel = "";
        if(nbLevel != 0){
            strLevel = "\nIl est monté de " + nbLevel + " level ce tour ci !";
        }
        monstre.setXp(xpTotal);
        save(monstre);
        return monstre.getNom() + " (id=" + monstre.getId() + ") est level " + monstre.getLevel()
                + " et il a actuellement " + monstre.getXp() + " expérience !" + strLevel;
    }
}
