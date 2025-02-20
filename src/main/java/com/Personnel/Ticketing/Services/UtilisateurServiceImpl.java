package com.Personnel.Ticketing.Services;

import com.Personnel.Ticketing.Models.Utilisateur;
import com.Personnel.Ticketing.Repository.UtilisateurDao;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UserDetailsService , UtilisateurService {

    private UtilisateurDao utilisateurDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void inscription(Utilisateur utilisateur){
        Optional<Utilisateur> utilisateurOptional = this.utilisateurDao.findByEmail(utilisateur.getEmail());
        if(utilisateurOptional.isPresent()){
            throw new RuntimeException("cet email est déja utilisé");
        }

        String mdpCrypte= this.bCryptPasswordEncoder.encode(utilisateur.getMdp());
        utilisateur.setMdp(mdpCrypte);
        this.utilisateurDao.save(utilisateur);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utilisateurDao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur n'a ete trouver avec ce email"));
    }
}
