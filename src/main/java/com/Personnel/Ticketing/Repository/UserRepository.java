package com.Personnel.Ticketing.Repository;


import com.Personnel.Ticketing.Models.Tickets;
import com.Personnel.Ticketing.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
}
