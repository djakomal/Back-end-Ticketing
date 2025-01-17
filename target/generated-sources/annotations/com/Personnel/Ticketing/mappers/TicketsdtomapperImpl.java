package com.Personnel.Ticketing.mappers;

import com.Personnel.Ticketing.Models.Tickets;
import com.Personnel.Ticketing.dtos.TicketsDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-17T11:32:34+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
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
        ticketsDto.setDescription( tickets.getDescription() );

        return ticketsDto;
    }

    @Override
    public TicketsDto mapToTicketsDto(Tickets tickets) {
        if ( tickets == null ) {
            return null;
        }

        TicketsDto ticketsDto = new TicketsDto();

        ticketsDto.setId( tickets.getId() );
        ticketsDto.setDescription( tickets.getDescription() );

        return ticketsDto;
    }

    @Override
    public Tickets mapToMail(TicketsDto ticketsDto) {
        if ( ticketsDto == null ) {
            return null;
        }

        Tickets tickets = new Tickets();

        tickets.setId( ticketsDto.getId() );
        tickets.setDescription( ticketsDto.getDescription() );

        return tickets;
    }
}
