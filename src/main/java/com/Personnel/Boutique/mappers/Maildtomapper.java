package com.Personnel.Boutique.mappers;


import com.Personnel.Boutique.Models.Mail;

import com.Personnel.Boutique.dtos.MailDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface Maildtomapper {
    Maildtomapper maildtomapper = Mappers.getMapper(Maildtomapper.class);
    //@Mapping(target = "name",ignore = true)
    MailDto mapToMailDto(Mail mail);
    Mail mapToMail(MailDto mailDto);

}
