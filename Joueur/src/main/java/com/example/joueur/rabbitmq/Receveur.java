package com.example.joueur.rabbitmq;

import com.example.joueur.model.Equipe;
import com.example.joueur.service.EquipeService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RabbitListener(queues = "hello")
public class Receveur {

    @Autowired
    private EquipeService equipeService;

    @RabbitHandler
    public void recevoir(String monstre) throws InterruptedException {
        List<Equipe> monstresEquipe = equipeService.getAllEquipe();
        if (monstresEquipe.size() >= equipeService.getMaxMonstres()) {
            Thread.sleep(2000);
            throw new InterruptedException("L'equipe est pleine");
        }
        String[] monstreInfo = monstre.split(",");
        int idMonstre = Integer.parseInt(monstreInfo[0]);
        Equipe newMonstreEquipe = new Equipe();
        newMonstreEquipe.setId(idMonstre);
        newMonstreEquipe.setNom(monstreInfo[1]);
        equipeService.save(newMonstreEquipe);
    }
}