package com.Personnel.Ticketing.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
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


}