package com.Personnel.Boutique.Repository;


import com.Personnel.Boutique.Models.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
    //List<Taxes> findByBillingId(Long id);
}
