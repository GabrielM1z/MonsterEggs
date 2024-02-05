package com.example.coffre.service;

import com.example.coffre.model.MonstreCoffre;
import com.example.coffre.repository.MonstreCoffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffreService {

    @Autowired
    private MonstreCoffreRepository monstreCoffreRepository;

    public MonstreCoffre save(MonstreCoffre monstreCoffre)
    {
        return monstreCoffreRepository.save(monstreCoffre);
    }

    public void delete(int id)
    {
        monstreCoffreRepository.deleteById(id);
    }

    public List<MonstreCoffre> getAllMonstreCoffre(){
        return (List<MonstreCoffre>) monstreCoffreRepository.findAll();
    }
}
