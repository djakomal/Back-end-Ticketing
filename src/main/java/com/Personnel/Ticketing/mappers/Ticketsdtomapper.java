package com.Personnel.Ticketing.mappers;


import com.Personnel.Ticketing.Models.Tickets;
import com.Personnel.Ticketing.dtos.TicketsDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface Ticketsdtomapper {
    Ticketsdtomapper maildtomapper = Mappers.getMapper(Ticketsdtomapper.class);
    //@Mapping(target = "name",ignore = true)
 TicketsDto mapToMailDto(Tickets tickets);

    TicketsDto mapToTicketsDto(Tickets tickets);

    Tickets mapToMail(TicketsDto ticketsDto);

}
