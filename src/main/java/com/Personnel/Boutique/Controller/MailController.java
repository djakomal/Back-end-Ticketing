package com.Personnel.Boutique.Controller;


import com.Personnel.Boutique.Models.Mail;

import com.Personnel.Boutique.Services.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/CRUD")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://localhost:5173")
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("")
    public List<Mail> getAll(){
        return mailService.getAll();
    }

    @PostMapping("/add")
    public Mail add(@RequestBody Mail mail){
        return  mailService.add(mail);
    }


    @PutMapping("/update/{Id}")
    public Mail update(@PathVariable("Id") Long Id,@RequestBody Mail mail){

        return  mailService.update(Id,mail);
    }

    @DeleteMapping("/delete/{id}")
    public  void delete(@PathVariable Long id){
        mailService.delete(id);
    }

    @GetMapping("/get/{id}")
    public  Mail getMail(@PathVariable Long id){
        return  mailService.getMail(id);
    }

}
