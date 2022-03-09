package com.enourato.worldsstrongest.dtos;


import com.enourato.worldsstrongest.entities.Fighters;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class FightersDto implements Serializable {
    private Long id;
    private String name;
    private String origin;
    private Integer hp;
    private Integer def;
    private List<AttackDto> attackList;
    private String imgUrl;



    public FightersDto(Fighters character) {
        this.id = character.getId();
        this.name = character.getName();
        this.origin = character.getOrigin();
        this.hp = character.getHp();
        this.def = character.getDef();
        this.attackList = character.getAttackSet().stream().map(attack -> new AttackDto(attack)).collect(Collectors.toList());
        this.imgUrl = character.getImgUrl();
    }

    public FightersDto(Long id, String name, String origin, Integer hp, Integer def, String imgUrl) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.hp = hp;
        this.def = def;
        this.imgUrl = imgUrl;
    }

    public FightersDto(Object[] objects) {
        this.id= (Long) objects[0];
        this.name= (String) objects[1];
        this.origin= (String) objects[2];
        this.hp= (Integer) objects[3];
        this.def= (Integer) objects[4];
        this.imgUrl= (String) objects[5];

    }
}
