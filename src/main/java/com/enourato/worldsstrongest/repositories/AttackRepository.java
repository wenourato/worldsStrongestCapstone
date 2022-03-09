package com.enourato.worldsstrongest.repositories;

import com.enourato.worldsstrongest.entities.Attack;
import com.enourato.worldsstrongest.entities.Fighters;
import com.enourato.worldsstrongest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttackRepository extends JpaRepository<Attack, Long> {
    List<Attack> findAttackByFighterEquals(Fighters fighter);

}