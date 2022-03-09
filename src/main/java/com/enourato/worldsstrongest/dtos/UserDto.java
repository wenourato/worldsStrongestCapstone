package com.enourato.worldsstrongest.dtos;

import com.enourato.worldsstrongest.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Date birthday;
    private String email;
    private String fname;
    private String lname;
    private FightersDto favoriteFighter;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.birthday = user.getBirthday();
        this.email = user.getEmail();
        this.fname = user.getFname();
        this.lname = user.getLname();
        this.favoriteFighter = new FightersDto(user.getFavoriteFighter());



    }
}
