package com.Personnel.Boutique.Services;

import com.Personnel.Boutique.Models.Mail;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MailService {
    List<Mail> getAll();

    Mail add(Mail mail);

    Mail update(Long Id,Mail mail);


    void delete(Long id);

    Mail getMail(Long id);


}
