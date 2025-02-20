package com.Personnel.Ticketing.Services;

import com.Personnel.Ticketing.Models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface UserService {
    List<User> getAll();

    User add(User user);

    User update(Long Id, User user);

    void delete(Long id);

    User getUser(Long id);

    User getByname(String name);




}
