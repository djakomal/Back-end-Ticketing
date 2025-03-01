package com.Personnel.Ticketing.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.lang.reflect.Field;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Ticket")
public class TicketsDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @Column(name = "Artiste")
    private String Artiste;


    @Column(name = "Categories")
    private String Categories;

    @Column(name = "Date")
    private String Date;

    @Column(name = "Budget")
    private Integer Budget;


    @Column(name = "Invited")
    private String Invited;

    @Column(name = "lieu")
    private String Lieu;

    @Column(name = "Jour")
    private String Jour;

    @Column(name = "mois")
    private String Mois;

    @Column(name = "photo")
    private File photo;


}