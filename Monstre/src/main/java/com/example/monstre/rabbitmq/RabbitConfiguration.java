package com.example.monstre.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Bean
    public Envoyeur getEnvoyer() {
        return new Envoyeur();
    }
}