package com.example.coffre.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MonstreCoffre {

    @Id
    private int id;

    private String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
