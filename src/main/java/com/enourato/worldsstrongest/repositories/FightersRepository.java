package com.enourato.worldsstrongest.repositories;

import com.enourato.worldsstrongest.entities.Cart;
import com.enourato.worldsstrongest.entities.Fighters;
import com.enourato.worldsstrongest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.cache.annotation.Cacheable;
import java.util.List;
import java.util.Optional;


@Repository
public interface FightersRepository extends JpaRepository<Fighters, Long> {
    Optional<Fighters> findByUserSetContaining(User user);

    List<Fighters> findAllByFighterCart(Cart cart);

//    @Cacheable(value = "fighters")
    @Query("select f.id, f.name, f.origin, f.hp, f.def, f.imgUrl from Fighters f order by f.id")

    List<Object[]> findAllLight();

}


