package com.enourato.worldsstrongest.controllers;

import com.enourato.worldsstrongest.dtos.UserDto;
import com.enourato.worldsstrongest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public String addNewUser(@RequestBody UserDto userDto){
        String hashPass = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(hashPass);
        return userService.addNewUser(userDto);
    }
    @PostMapping("/login")
    public List<String> userLogin (@RequestBody UserDto userDto) { return userService.userLogin(userDto);
    }
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/{userId}/fighter/{fighterId}")
    public String addFighterToUser(@PathVariable Long userId, @PathVariable Long fighterId) {
        return userService.addFighterToUser(userId, fighterId);


    }
    @PostMapping("/cart/{cartId}/fighter/{fighterId}")
    public String addFighterToUserCart(@PathVariable Long cartId, @PathVariable Long fighterId) {
        return userService.addFighterToUserCart(cartId, fighterId);


    }

}



