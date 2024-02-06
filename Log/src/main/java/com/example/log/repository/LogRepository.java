package com.example.log.repository;

import com.example.log.model.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Integer> {
}
