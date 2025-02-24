package com.Personnel.Ticketing.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Utilisateur")
public class Utilisateur implements UserDetails {

    @Id
    @GeneratedValue
    private int id;
    private String mdp ;
    private String email;
    private String nom;
    private String prenom;
    private boolean actif =true;
    @OneToOne(cascade = CascadeType.ALL)
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+this.role.getLibelle()));
    }

    @Override
    public String getPassword() {
        return this.mdp;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isAccountNonLocked() {
        return   this.actif;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return   this.actif;
    }

    @Override
    public boolean isEnabled() {
        return   this.actif;
    }
}
