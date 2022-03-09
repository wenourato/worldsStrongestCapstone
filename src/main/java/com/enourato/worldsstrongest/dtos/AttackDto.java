package com.enourato.worldsstrongest.dtos;

import com.enourato.worldsstrongest.entities.Attack;
import com.enourato.worldsstrongest.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class AttackDto {
    private Long id;
    private String AtkName;
    private Integer AtkValue;


    public AttackDto(Attack attack) {
        this.id = attack.getId();
        this.AtkName =attack.getAtkName();
        this.AtkValue = attack.getAtkValue();



    }
}
