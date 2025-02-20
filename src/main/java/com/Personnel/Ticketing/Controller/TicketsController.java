package com.Personnel.Ticketing.Controller;


import com.Personnel.Ticketing.Models.Tickets;
import com.Personnel.Ticketing.Services.TicketsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@Controller
@RestController
@RequestMapping("/tickets")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://localhost:8080/")
public class TicketsController {

    @Autowired
    private TicketsService ticketsService;
    @Autowired
    private static final String UPLOAD_DIR = "uploads/";

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




    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Redimensionner l'image à 300x300 pixels
            String imagePath = ticketsService.saveAndResizeImage(file, 300, 300);
            return ResponseEntity.ok("Image uploaded successfully: " + imagePath);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error uploading image: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public  void delete(@PathVariable Long id){
        ticketsService.delete(id);
    }

    @GetMapping("/get/{id}")
    public Tickets getTickets(@PathVariable Long id){
        return  ticketsService.getTickets(id);
    }

    @GetMapping("/get/email/{email}")
    public Tickets getByname(@PathVariable("email") String  name){
        return  ticketsService.getByname(name);
    }



}
