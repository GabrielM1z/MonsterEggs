package com.example.joueur.model;

import jakarta.persistence.*;

@Entity
public class Inventaire {

    @Id
    private String type;
    private int quantity;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}