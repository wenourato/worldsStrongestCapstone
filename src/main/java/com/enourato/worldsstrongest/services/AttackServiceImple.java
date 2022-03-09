package com.enourato.worldsstrongest.services;

import com.enourato.worldsstrongest.dtos.AttackDto;
import com.enourato.worldsstrongest.entities.Attack;
import com.enourato.worldsstrongest.entities.Fighters;
import com.enourato.worldsstrongest.repositories.AttackRepository;
import com.enourato.worldsstrongest.repositories.FightersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AttackServiceImple implements AttackService{

    @Autowired
    AttackRepository attackRepository;
    @Autowired
    FightersRepository fightersRepository;


    @Override
    public List<AttackDto> getAttacks() {
        List<Attack> attackList = attackRepository.findAll();
        return attackList.stream().map(attack -> new AttackDto(attack)).collect(Collectors.toList());
    }
    @Override
    public List<AttackDto> getAttacksByFighter(Long fighterId){
        Optional<Fighters> fightersOptional = fightersRepository.findById(fighterId);
        if (fightersOptional.isPresent()) {
            List<Attack> attackList = attackRepository.findAttackByFighterEquals(fightersOptional.get());
            return attackList.stream().map(attack -> new AttackDto(attack)).collect(Collectors.toList());
        }
        return Collections.emptyList();

    }

}
