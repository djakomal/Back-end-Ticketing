package com.Personnel.Boutique.Services;



import com.Personnel.Boutique.Exception.MailException;

import com.Personnel.Boutique.Models.Mail;

import com.Personnel.Boutique.Repository.MailRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class MailServiceImpl implements MailService   {

    @Autowired
    private MailRepository mailRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Mail> getAll() {
        //List<Taxes> taxe= taxesRepository.findAll();
        return mailRepository.findAll();
        //return  taxe.stream().map((taxes -> modelMapper.map(taxes,TaxesDto.class)))
                //.collect(Collectors.toList());
    }

    @Override
    public Mail add(Mail mail) {
       // Boisson boisson = boissondtomapper.mapToBoisson(boissonDto);
        Mail savedmail = mailRepository.save(mail);
        //BoissonDto savedBoissonDto = boissondtomapper.mapToBoissonDto(savedboisson);
        return  savedmail;
    }

    @Override
    public Mail update(Long Id,Mail mail) {
        Mail existingMail = mailRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Mail with ID " + Id + " not found"));

        // Mettre à jour les champs
        if (mail.getEmail() != null) existingMail.setEmail(mail.getEmail());
        if (mail.getReciver() != null) existingMail.setReciver(mail.getReciver());
        if (mail.getObjet() != null) existingMail.setObjet(mail.getObjet());
        if (mail.getDescription() != null) existingMail.setDescription(mail.getDescription());

        // Enregistrer les modifications
        return mailRepository.save(existingMail);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new MailException("ID cannot be null");
        }
        Optional<Mail> optionalTaxes = mailRepository.findById(id);
        if (!optionalTaxes.isPresent()) {
            throw new MailException("Taxes with ID " + id + " not found");
        }
        mailRepository.deleteById(id);

    }

    @Override
    public Mail getMail(Long id) {
        Mail mail = mailRepository.findById(id).orElse(null);
        return mail;
    }


    @Override
    public Mail getByemail(String email) {
        return (Mail) mailRepository.findByEmail(email);

    }


}
