package com.enourato.worldsstrongest.services;

import com.enourato.worldsstrongest.dtos.UserDto;
import com.enourato.worldsstrongest.entities.Cart;
import com.enourato.worldsstrongest.entities.Fighters;
import com.enourato.worldsstrongest.entities.User;
import com.enourato.worldsstrongest.repositories.CartRepository;
import com.enourato.worldsstrongest.repositories.FightersRepository;
import com.enourato.worldsstrongest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@Service
public class UserServiceImple implements UserService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    FightersRepository fighterRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public String addNewUser(UserDto userDto){
        User user = new User(userDto);
        Cart cart = new Cart();
        user.setCart(cart);
        cartRepository.save(cart);
        userRepository.saveAndFlush(user);
        return "New user added successfully!";
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> new UserDto(user)).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public String addFighterToUser(Long userId, Long fighterId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Fighters> fightersOptional = fighterRepository.findById(fighterId);
        if(userOptional.isPresent() && fightersOptional.isPresent()) {
            userOptional.get().setFavoriteFighter(fightersOptional.get());
//            fightersOptional.get().getUserSet().add(userOptional.get());
            userRepository.saveAndFlush(userOptional.get());
            return "Favorite fighter has been saved!";
        }
        return "Error. Favorite fighter has not been assigned.";
    }

    @Override
    public List<String> userLogin(UserDto userDto) {
        List<String> response = new ArrayList<>();//ArrayList to house our response elements
        Optional<User> user = userRepository.findByUsername(userDto.getUsername()); //Optional to let us handle nulls
        if (user.isPresent()) { //Check the optional to see if its empty or not
            //Logic for what you want to do
            if (passwordEncoder.matches(userDto.getPassword(), user.get().getPassword())) {
                response.add("http://localhost:8081/index.html");
            } else {
                response.add("Username or Password Incorrect");
            }
            response.add(String.valueOf(user.get().getId()));
            response.add(String.valueOf(user.get().getCart().getId()));
            if(user.get().getFavoriteFighter() != null){
                response.add(String.valueOf(user.get().getFavoriteFighter().getId()));
            }
        } else{
            response.add("Username or Password Incorrect");
        }
        return response;
    }

    @Override
    @Transactional
    public String addFighterToUserCart(Long cartId, Long fighterId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        Optional<Fighters> fightersOptional = fighterRepository.findById((fighterId));
        if(cartOptional.isPresent() && fightersOptional.isPresent()){
            fightersOptional.get().setFighterCart(cartOptional.get());
            fighterRepository.saveAndFlush(fightersOptional.get());
            return "Fighter added to cart!";
        }
        return "Unable to add to cart";
    }
}
