package com.example.monstre.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class Envoyeur {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    public void envoyer(String monstre) {
        template.convertAndSend(queue.getName(), monstre);
    }
}