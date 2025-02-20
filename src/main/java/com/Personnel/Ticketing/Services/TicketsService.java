package com.Personnel.Ticketing.Services;

import com.Personnel.Ticketing.Models.Tickets;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface TicketsService {
    List<Tickets> getAll();

    Tickets add(Tickets tickets);

   Tickets update(Long Id, Tickets tickets);


    void delete(Long id);

Tickets getTickets(Long id);


  Tickets getByname(String name);


    String saveAndResizeImage(MultipartFile file, int i, int i1)throws IOException;
}
