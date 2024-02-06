package com.example.log.service;

import com.example.log.model.Log;
import com.example.log.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public Log save(Log log){
        return logRepository.save(log);
    }

    public void delete(int id) {
        logRepository.deleteById(id);
    }

    public void deleteAll()
    {
        logRepository.deleteAll();
    }

    public List<Log> getAllLog() {
        return (List<Log>) logRepository.findAll();
    }

}
