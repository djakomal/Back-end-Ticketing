package com.Personnel.Ticketing.Services;

import com.Personnel.Ticketing.Exception.TicketingException;
import com.Personnel.Ticketing.Models.Tickets;
import com.Personnel.Ticketing.Repository.TicketsRepository;
import net.coobird.thumbnailator.Thumbnails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
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
        if (tickets.getLieu() != null) existingTickets.setLieu(tickets.getLieu());
        if (tickets.getMois() != null) existingTickets.setMois(tickets.getMois());
        if (tickets.getJour() != null) existingTickets.setJour(tickets.getJour());


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

    @Override
    public String saveAndResizeImage(MultipartFile file, int width, int height) throws IOException {
        // Définir le chemin de sauvegarde
        String uploadDir = "uploads/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // Chemin complet du fichier
        String filePath = uploadDir + file.getOriginalFilename();

        // Redimensionner et enregistrer l'image
        Thumbnails.of(file.getInputStream())
                .size(width, height)
                .toFile(filePath);

        return filePath; // Retourner le chemin de l'image sauvegardée
    }


}
