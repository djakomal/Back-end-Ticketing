package com.Personnel.Ticketing.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;



    @Column(name = "email")
    private String email;



    @Column(name = "password")
    private String password;

    @Column(name = "confirme_password")
    private String confirme_password;


}
