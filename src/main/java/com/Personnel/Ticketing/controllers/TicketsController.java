
package com.Personnel.Ticketing.controllers;

import com.Personnel.Ticketing.Models.Tickets;
import com.Personnel.Ticketing.Services.TicketsService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RestController
@RequestMapping({"/tickets"})
@CrossOrigin(
        origins = {"http://localhost:8080/"}
)
public class TicketsController {
    @Autowired
    private TicketsService ticketsService;
    @Autowired
    private static final String UPLOAD_DIR = "uploads/";

    public TicketsController() {
    }

    @GetMapping({""})
    public List<Tickets> getAll() {
        return this.ticketsService.getAll();
    }

    @PostMapping({"/add"})
    public Tickets add(@RequestBody Tickets tickets) {
        return this.ticketsService.add(tickets);
    }

    @PutMapping({"/update/{Id}"})
    public Tickets update(@PathVariable("Id") Long Id, @RequestBody Tickets tickets) {
        return this.ticketsService.update(Id, tickets);
    }

    @PostMapping({"/upload"})
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imagePath = this.ticketsService.saveAndResizeImage(file, 300, 300);
            return ResponseEntity.ok("Image uploaded successfully: " + imagePath);
        } catch (IOException var3) {
            return ResponseEntity.badRequest().body("Error uploading image: " + var3.getMessage());
        }
    }

    @DeleteMapping({"/delete/{id}"})
    public void delete(@PathVariable Long id) {
        this.ticketsService.delete(id);
    }

    @GetMapping({"/get/{id}"})
    public Tickets getTickets(@PathVariable Long id) {
        return this.ticketsService.getTickets(id);
    }

    @GetMapping({"/get/email/{email}"})
    public Tickets getByname(@PathVariable("email") String name) {
        return this.ticketsService.getByname(name);
    }
}
