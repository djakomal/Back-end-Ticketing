package com.Personnel.Ticketing.Controller;

import com.Personnel.Ticketing.Models.Tickets;
import com.Personnel.Ticketing.Models.User;
import com.Personnel.Ticketing.Services.TicketsService;
import com.Personnel.Ticketing.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/User")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://localhost:8080/")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping("/add")
    public User add(@RequestBody User user){
        return  userService.add(user);
    }


    @PutMapping("/update/{Id}")
    public User update(@PathVariable("Id") Long Id, @RequestBody User user){

        return  userService.update(Id, user);
    }

    @DeleteMapping("/delete/{id}")
    public  void delete(@PathVariable Long id){
        userService.delete(id);
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable Long id){
        return  userService.getUser(id);
    }

    @GetMapping("/get/email/{email}")
    public User getByname(@PathVariable("email") String  email){
        return  userService.getByname(email);
    }


}
