package com.Personnel.Ticketing.Repository;


import com.Personnel.Ticketing.Models.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;


@Repository
public interface JwtDao extends JpaRepository<Jwt,Long> {

    Optional<Jwt> findByValue(String value);

    Optional<Jwt> findByValueAndDesactiveAndExpire(String valeur, boolean desactive, boolean expire);

    @Query("FROM Jwt j WHERE j.expire = :expire AND j.desactive = :desactive AND j.utilisateur.email = :email")
    Optional<Jwt> findUtilisateurValidToken(String email, boolean desactive, boolean expire);

    @Query("FROM Jwt j WHERE  j.utilisateur.email = :email")
    Stream<Jwt> findUtilisateur(String email);

    void deleteAllByExpireAndDesactive(boolean expire, boolean desactive);
}
