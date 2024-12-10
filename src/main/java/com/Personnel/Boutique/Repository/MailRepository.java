package com.Personnel.Boutique.Repository;


import com.Personnel.Boutique.Models.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {


    Mail  findByEmail(String email);
}
