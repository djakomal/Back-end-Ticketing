package com.Personnel.Ticketing.Services;

import com.Personnel.Ticketing.Exception.TicketingException;
import com.Personnel.Ticketing.Models.User;
import com.Personnel.Ticketing.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService{


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<User> getAll() {
        //List<Taxes> taxe= taxesRepository.findAll();
        return userRepository.findAll();
        //return  taxe.stream().map((taxes -> modelMapper.map(taxes,TaxesDto.class)))
        //.collect(Collectors.toList());
    }

    @Override
    public User add(User user) {
        // Boisson boisson = boissondtomapper.mapToBoisson(boissonDto);
        User saveduser = userRepository.save(user);
        //BoissonDto savedBoissonDto = boissondtomapper.mapToBoissonDto(savedboisson);
        return  saveduser;
    }

    @Override
    public User update(Long Id, User user) {
        User existingUser = userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Tickets with ID " + Id + " not found"));

        // Mettre à jour les champs
        if (user.getName() != null) existingUser.setName(user.getName());
        if (user.getPassword() != null) existingUser.setPassword(user.getPassword());
        if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
        if (user.getConfirme_password() != null) existingUser.setConfirme_password(user.getConfirme_password());


        // Enregistrer les modifications
        return userRepository.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new TicketingException("ID cannot be null");
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new TicketingException("Tickets with ID " + id + " not found");
        }
        userRepository.deleteById(id);

    }

    @Override
    public User getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }


    @Override
    public User getByname(String name) {
        return  userRepository.findByName(name);

    }


}
