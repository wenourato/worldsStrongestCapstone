package com.enourato.worldsstrongest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attacks")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Attack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false, name = "atk_name")
    private String atkName;
    @Column(nullable = false, name = "atk_value")
    private Integer atkValue;

   @ManyToOne(fetch=FetchType.LAZY)
   @JsonBackReference
    Fighters fighter;




}
