package com.Personnel.Ticketing.Models;



import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Jwt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 65555)
    private  String value;
    private  boolean desactive;
    private boolean expire;

//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    private RefreshToken refreshToken;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE} )
    private Utilisateur utilisateur;
}

