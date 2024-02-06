package com.example.combat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/combat")
public class CombatController {
    /**
     * Route de vérification service up
     */
    @GetMapping(path="/main")
    public @ResponseBody String main(){
        return "OK";
    }
}
