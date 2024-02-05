package com.example.joueur.repository;

import com.example.joueur.model.Inventaire;
import org.springframework.data.repository.CrudRepository;

public interface InventaireRepository extends CrudRepository<Inventaire, Integer> {

    public Inventaire findByType(String type);
}
