package com.enourato.worldsstrongest.services;


import com.enourato.worldsstrongest.entities.Cart;
import com.enourato.worldsstrongest.entities.Fighters;
import com.enourato.worldsstrongest.dtos.FightersDto;
import com.enourato.worldsstrongest.entities.User;
import com.enourato.worldsstrongest.repositories.AttackRepository;
import com.enourato.worldsstrongest.repositories.CartRepository;
import com.enourato.worldsstrongest.repositories.FightersRepository;
import com.enourato.worldsstrongest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Cacheable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FightersServiceImple implements FightersService {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    FightersRepository fightersRepository;
    @Autowired
    AttackRepository attackRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;

    @Override

    public List<FightersDto> getFighters() {
        List<Object[]> fightersList = fightersRepository.findAllLight();
//                entityManager.createNativeQuery("select f.id, f.origin, f.name, f.hp, f.def, f.imgUrl from Fighters f", FightersDto.class).getResultList();
        return fightersList.stream().map(FightersDto::new).collect(Collectors.toList());

    }

    @Override
    public Optional<FightersDto> getUserFavoriteFighter(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            if (userOptional.get().getFavoriteFighter() != null) {
                return Optional.of(new FightersDto(userOptional.get().getFavoriteFighter()));
            }

        }
        return Optional.empty();
    }

    @Override
    public Optional<FightersDto> getFightersById(Long fighterId) {
        Optional<Fighters> fighterOptional = fightersRepository.findById(fighterId);
        if (fighterOptional.isPresent()) {
        return Optional.of(new FightersDto(fighterOptional.get()));
        }

        return Optional.empty();

    }

    @Override
    public List<FightersDto> getFightersByCart(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            List<Fighters> fightersList = fightersRepository.findAllByFighterCart(cartOptional.get());
            return fightersList.stream().map(fighters -> new FightersDto(fighters)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}

