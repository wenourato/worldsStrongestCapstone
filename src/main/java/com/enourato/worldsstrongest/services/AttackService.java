package com.enourato.worldsstrongest.services;

import com.enourato.worldsstrongest.dtos.AttackDto;

import java.util.List;

public interface AttackService {

    List<AttackDto> getAttacks();

    List<AttackDto> getAttacksByFighter(Long fighterId);
}
