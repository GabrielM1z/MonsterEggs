package com.example.log.controller;

import com.example.log.model.Log;
import com.example.log.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/log")
public class LogController
{

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    /**
     * Route de vérification service up
     */
    @GetMapping(path="/main")
    public @ResponseBody String main(){
        return "OK";
    }

    /**
     * Route pour ajouter un log
     * @param description
     * @return
     */
    @GetMapping("/add/{description}")
    public Log add(@PathVariable String description)
    {
        // creation d'un log
        Log log = new Log();

        // ajout de sa description
        log.setDescription(description);

        // return du log sauvegardé
        return logService.save(log);
    }


    /**
     * Route pour recupéré tout les logs
     * @return
     */
    @GetMapping("/all")
    public @ResponseBody Iterable<Log> getAll() {
        return logService.getAllLog();
    }

}
