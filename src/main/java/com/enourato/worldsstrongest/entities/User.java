package com.enourato.worldsstrongest.entities;

import com.enourato.worldsstrongest.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity //marks as entity to be kept track of in hibernate
@Table(name = "users") //creats table
@Data //opens up getters and setters
@NoArgsConstructor //these are the constructors for my class...makes life easy so i dont have to type
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50, nullable = false)
    private String username;
    @Column
    private String password;
    private Date birthday;
    @Column(length = 50)
    private String email;
    private String fname;
    private String lname;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private Fighters favoriteFighter;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            uniqueConstraints = {@UniqueConstraint(columnNames = {"users_id", "fighter_id"})},
            name = "user_fighters",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "fighter_id")}

    )
    @EqualsAndHashCode.Exclude
    private Set<Fighters> fightersSet = new HashSet<>();



    public void addCharacter(Fighters fighters) {
        fightersSet.add(fighters);
        fighters.getUserSet().add(this);

    }

    public User(UserDto userDto){
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.birthday = userDto.getBirthday();
        this.email = userDto.getEmail();
        this.fname = userDto.getFname();
        this.lname = userDto.getLname();


    }

    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    public User(Long id, String username, String password, Date birthday, String email, String fname, String lname, Fighters favoriteFighter, Set<Fighters> fightersSet) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.favoriteFighter = favoriteFighter;
        this.fightersSet = fightersSet;

    }
}

