package ru.akorsa.springdata.jpa.service;

import ru.akorsa.springdata.jpa.model.Contact;

import java.util.List;

public interface ContactJpaService {

    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}
