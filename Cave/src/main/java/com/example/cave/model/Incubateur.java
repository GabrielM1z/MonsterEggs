package com.example.cave.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Incubateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean oeuf;

    private LocalDateTime dateEclosion;

    public LocalDateTime getDateEclosion() {
        return dateEclosion;
    }

    public void setDateEclosion(LocalDateTime dateEclosion) {
        this.dateEclosion = dateEclosion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean hasOeuf() {
        return oeuf;
    }

    public void setOeuf(boolean oeuf) {
        this.oeuf = oeuf;
    }

    public void resetOeuf(){
        setOeuf(false);
        setDateEclosion(LocalDateTime.now());
    }
}
