package com.Personnel.Ticketing.Services;



import com.Personnel.Ticketing.Exception.TicketingException;


import com.Personnel.Ticketing.Models.Tickets;
import com.Personnel.Ticketing.Repository.TicketsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class TicketsServiceImpl implements TicketsService   {

    @Autowired
    private TicketsRepository ticketsRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Tickets> getAll() {
        //List<Taxes> taxe= taxesRepository.findAll();
        return ticketsRepository.findAll();
        //return  taxe.stream().map((taxes -> modelMapper.map(taxes,TaxesDto.class)))
                //.collect(Collectors.toList());
    }

    @Override
    public Tickets add(Tickets tickets) {
       // Boisson boisson = boissondtomapper.mapToBoisson(boissonDto);
       Tickets savedtickets = ticketsRepository.save(tickets);
        //BoissonDto savedBoissonDto = boissondtomapper.mapToBoissonDto(savedboisson);
        return  savedtickets;
    }

    @Override
    public Tickets update(Long Id, Tickets tickets) {
        Tickets existingTickets = ticketsRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Tickets with ID " + Id + " not found"));

        // Mettre à jour les champs
        if (tickets.getName() != null) existingTickets.setName(tickets.getName());
        if (tickets.getArtiste() != null) existingTickets.setArtiste(tickets.getArtiste());
        if (tickets.getCategories() != null) existingTickets.setCategories(tickets.getCategories());
        if (tickets.getDate() != null) existingTickets.setDate(tickets.getDate());
        if (tickets.getBudget() != null) existingTickets.setBudget(tickets.getBudget());
        if (tickets.getInvited() != null) existingTickets.setInvited(tickets.getInvited());


        // Enregistrer les modifications
        return ticketsRepository.save(existingTickets);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new TicketingException("ID cannot be null");
        }
        Optional<Tickets> optionalTaxes = ticketsRepository.findById(id);
        if (!optionalTaxes.isPresent()) {
            throw new TicketingException("Tickets with ID " + id + " not found");
        }
        ticketsRepository.deleteById(id);

    }

    @Override
    public Tickets getTickets(Long id) {
        Tickets tickets = ticketsRepository.findById(id).orElse(null);
        return tickets;
    }


    @Override
    public Tickets getByname(String name) {
        return  ticketsRepository.findByName(name);

    }


}
