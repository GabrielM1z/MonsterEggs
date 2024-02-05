package com.example.joueur.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

@Entity // This tells Hibernate to make a table out of this class
public class Inventaire {

    @Id
    private String nameObject;
    private Integer quantity;
}