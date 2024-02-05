package com.example.boutique.service;

import com.example.boutique.model.Boutique;
import com.example.boutique.repository.BoutiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoutiqueService {

    @Autowired
    private BoutiqueRepository boutiqueRepository;

    public Boutique save(Boutique boutique){
        return boutiqueRepository.save(boutique);
    }

    public Boutique getItem(int ItemId)
    {
        Optional<Boutique> item =  boutiqueRepository.findById(ItemId);
        return item.orElse(null);
    }

    public List<Boutique> getAllBoutique() {
        return (List<Boutique>) boutiqueRepository.findAll();
    }

    public void deleteAll(){
        boutiqueRepository.deleteAll();
    }

    public void deleteById(int id){
        boutiqueRepository.deleteById(id);
    }
}
