package ru.akorsa.springdata.jpa.service;

import ru.akorsa.springdata.jpa.model.ContactEntity;

import java.util.List;

public interface ContactEntityService {

    List<ContactEntity> findAll();
    List<ContactEntity> findByFirstName(String firstName);
    List<ContactEntity> findByFirstNameAndLastName(String firstName, String lastName);
    List<ContactEntity> getContactsWithDetail();
}
