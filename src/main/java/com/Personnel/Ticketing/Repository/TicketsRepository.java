package com.Personnel.Ticketing.Repository;


import com.Personnel.Ticketing.Models.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {


   Tickets findByName(String name);
}
