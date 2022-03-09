package com.enourato.worldsstrongest.controllers;


import com.enourato.worldsstrongest.dtos.AttackDto;
import com.enourato.worldsstrongest.dtos.FightersDto;
import com.enourato.worldsstrongest.entities.Attack;
import com.enourato.worldsstrongest.entities.Fighters;
import com.enourato.worldsstrongest.services.AttackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attack")
public class AttackController {
    @Autowired
    AttackService attackService;
    @GetMapping
    public List<AttackDto> getAllAttacks(){
        return attackService.getAttacks();
    }
    @GetMapping("/{fighterId}")
    public List<AttackDto> getAttacksByFighter(@PathVariable Long fighterId){
        return attackService.getAttacksByFighter(fighterId);
    }
}



