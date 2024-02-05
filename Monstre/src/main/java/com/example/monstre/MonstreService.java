package com.example.monstre;

import com.example.monstre.model.Monstre;
import com.example.monstre.repository.MonstreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonstreService {

    @Autowired
    private MonstreRepository monstreRepository;

    public Monstre save(Monstre monstre) {
        return monstreRepository.save(monstre);
    }

    public List<Monstre> getAllMonstre() {
        return (List<Monstre>) monstreRepository.findAll();
    }
}
