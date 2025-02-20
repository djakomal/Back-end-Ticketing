package com.Personnel.Ticketing.Controller;



import com.Personnel.Ticketing.Models.Utilisateur;
import com.Personnel.Ticketing.Services.UtilisateurServiceImpl;
import com.Personnel.Ticketing.dtos.AuthentificationDto;
import com.Personnel.Ticketing.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/Utilisateur")
public class UtilisateurController {

    private JwtService jwtService;
    private UtilisateurServiceImpl utilisateurService;
    private AuthenticationManager authenticationManager;
    @PostMapping(path ="inscription")
    public void inscription(@RequestBody Utilisateur utilisateur){
        //log.info("inscription");
        this.utilisateurService.inscription(utilisateur);

    }

    @PostMapping(path ="login")
    public Map<String, String> connexion(@RequestBody AuthentificationDto authentificationDto){
        //log.info("inscription");

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationDto.username(), authentificationDto.password()));
        if(authentication.isAuthenticated()){
            return this.jwtService.generate(authentificationDto.username());
        }
        return null;

    }

    @PostMapping(path ="logout")
    public void deconnexion(){
        //log.info("inscription");
        this.jwtService.deconnexion();

    }
}
