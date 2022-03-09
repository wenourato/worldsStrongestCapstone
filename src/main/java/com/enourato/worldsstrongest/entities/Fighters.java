package com.enourato.worldsstrongest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "fighters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fighters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50, nullable = false)
    private String name;
    @Column(length = 50)
    private String origin;
    @Column(nullable = false)
    private Integer hp;
    private Integer def;
    @Column
    private String imgUrl;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "favoriteFighter")
    @JsonManagedReference
    private Set<User> usersFavoriteSet = new HashSet<>();



    @OneToMany(mappedBy = "fighter", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private List<Attack> attackSet;



    @ManyToMany(mappedBy = "fightersSet", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude //this is preventing an error.....if you dont do this Spring wont be able to tell if their equal and wont build relationship
    Set<User> userSet = new HashSet<>();

    @ManyToOne
    @JsonBackReference
    Cart fighterCart;


    }

