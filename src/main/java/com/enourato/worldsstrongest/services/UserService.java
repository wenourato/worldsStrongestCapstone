package com.enourato.worldsstrongest.services;

import com.enourato.worldsstrongest.dtos.UserDto;

import java.util.List;

public interface UserService {
    String addNewUser(UserDto userDto);

    List<UserDto> getAllUsers();

    String addFighterToUser(Long userId, Long fighterId);

    List<String> userLogin(UserDto userDto);

    String addFighterToUserCart(Long cartId, Long fighterId);
}
