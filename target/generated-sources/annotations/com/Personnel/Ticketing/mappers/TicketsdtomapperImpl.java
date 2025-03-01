package com.Personnel.Ticketing.mappers;

import com.Personnel.Ticketing.Models.Tickets;
import com.Personnel.Ticketing.dto.TicketsDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-01T01:50:23+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class TicketsdtomapperImpl implements Ticketsdtomapper {

    @Override
    public TicketsDto mapToMailDto(Tickets tickets) {
        if ( tickets == null ) {
            return null;
        }

        TicketsDto ticketsDto = new TicketsDto();

        ticketsDto.setId( tickets.getId() );
        ticketsDto.setName( tickets.getName() );
        ticketsDto.setDescription( tickets.getDescription() );
        ticketsDto.setArtiste( tickets.getArtiste() );
        ticketsDto.setCategories( tickets.getCategories() );
        ticketsDto.setDate( tickets.getDate() );
        ticketsDto.setBudget( tickets.getBudget() );
        ticketsDto.setInvited( tickets.getInvited() );
        ticketsDto.setLieu( tickets.getLieu() );
        ticketsDto.setJour( tickets.getJour() );
        ticketsDto.setMois( tickets.getMois() );
        ticketsDto.setPhoto( tickets.getPhoto() );

        return ticketsDto;
    }

    @Override
    public TicketsDto mapToTicketsDto(Tickets tickets) {
        if ( tickets == null ) {
            return null;
        }

        TicketsDto ticketsDto = new TicketsDto();

        ticketsDto.setId( tickets.getId() );
        ticketsDto.setName( tickets.getName() );
        ticketsDto.setDescription( tickets.getDescription() );
        ticketsDto.setArtiste( tickets.getArtiste() );
        ticketsDto.setCategories( tickets.getCategories() );
        ticketsDto.setDate( tickets.getDate() );
        ticketsDto.setBudget( tickets.getBudget() );
        ticketsDto.setInvited( tickets.getInvited() );
        ticketsDto.setLieu( tickets.getLieu() );
        ticketsDto.setJour( tickets.getJour() );
        ticketsDto.setMois( tickets.getMois() );
        ticketsDto.setPhoto( tickets.getPhoto() );

        return ticketsDto;
    }

    @Override
    public Tickets mapToMail(TicketsDto ticketsDto) {
        if ( ticketsDto == null ) {
            return null;
        }

        Tickets tickets = new Tickets();

        tickets.setId( ticketsDto.getId() );
        tickets.setName( ticketsDto.getName() );
        tickets.setDescription( ticketsDto.getDescription() );
        tickets.setArtiste( ticketsDto.getArtiste() );
        tickets.setCategories( ticketsDto.getCategories() );
        tickets.setDate( ticketsDto.getDate() );
        tickets.setBudget( ticketsDto.getBudget() );
        tickets.setInvited( ticketsDto.getInvited() );
        tickets.setLieu( ticketsDto.getLieu() );
        tickets.setJour( ticketsDto.getJour() );
        tickets.setMois( ticketsDto.getMois() );
        tickets.setPhoto( ticketsDto.getPhoto() );

        return tickets;
    }
}
