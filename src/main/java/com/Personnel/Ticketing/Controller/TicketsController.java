package com.Personnel.Ticketing.Controller;


import com.Personnel.Ticketing.Models.Tickets;
import com.Personnel.Ticketing.Services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/tickets")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://localhost:8080/")
public class TicketsController {

    @Autowired
    private TicketsService ticketsService;

    @GetMapping("")
    public List<Tickets> getAll(){
        return ticketsService.getAll();
    }

    @PostMapping("/add")
    public Tickets add(@RequestBody Tickets tickets){
        return  ticketsService.add(tickets);
    }


    @PutMapping("/update/{Id}")
    public Tickets update(@PathVariable("Id") Long Id, @RequestBody Tickets tickets){

        return  ticketsService.update(Id, tickets);
    }

    @DeleteMapping("/delete/{id}")
    public  void delete(@PathVariable Long id){
        ticketsService.delete(id);
    }

    @GetMapping("/get/{id}")
    public Tickets getTickets(@PathVariable Long id){
        return  ticketsService.getTickets(id);
    }

    @GetMapping("/get/name/{name}")
    public Tickets getByname(@PathVariable("name") String  email){
        return  ticketsService.getByname(email);
    }



}
