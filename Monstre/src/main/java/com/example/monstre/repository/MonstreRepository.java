package com.example.monstre.repository;

import com.example.monstre.model.Monstre;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface MonstreRepository extends CrudRepository<Monstre, Integer> {

}
