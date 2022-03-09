package com.enourato.worldsstrongest.services;

import com.enourato.worldsstrongest.dtos.FightersDto;

import java.util.List;
import java.util.Optional;

public interface FightersService {
    List<FightersDto> getFighters();

    Optional<FightersDto> getUserFavoriteFighter(Long userId);

    Optional<FightersDto> getFightersById(Long fighterId);

    List<FightersDto> getFightersByCart(Long cartId);
}
