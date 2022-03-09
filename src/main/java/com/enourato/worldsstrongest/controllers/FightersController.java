package com.enourato.worldsstrongest.controllers;

import com.enourato.worldsstrongest.dtos.FightersDto;
import com.enourato.worldsstrongest.services.FightersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fighters")
public class FightersController {
    @Autowired
    FightersService fightersService;

    @GetMapping
    public List<FightersDto> getAllFighters() {
        return fightersService.getFighters();

    }

    @GetMapping("/users/{userId}")
    public Optional<FightersDto> getUserFavoriteFighter(@PathVariable Long userId) {
        return fightersService.getUserFavoriteFighter(userId);


    }

    @GetMapping("/{fighterId}")
    public Optional<FightersDto> getFighterById(@PathVariable Long fighterId) {
        return fightersService.getFightersById(fighterId);
    }

    @GetMapping("/cart/{cartId}")
    public List<FightersDto> getFightersByCart(@PathVariable Long cartId) {
        return fightersService.getFightersByCart(cartId);
    }
}


