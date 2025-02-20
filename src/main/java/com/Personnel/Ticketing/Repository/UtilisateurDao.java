package com.Personnel.Ticketing.Repository;

import com.Personnel.Ticketing.Models.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDao extends CrudRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByEmail(String email);
}
