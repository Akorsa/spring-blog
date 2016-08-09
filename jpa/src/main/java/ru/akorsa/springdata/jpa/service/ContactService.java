package ru.akorsa.springdata.jpa.service;

import ru.akorsa.springdata.jpa.dto.ContactDTO;
import ru.akorsa.springdata.jpa.model.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
    List<Contact> getContactsWithDetail();
    void save(Contact contact);
    public Contact update(ContactDTO updated) throws NotFoundException;

    Contact findById(Long ID);
    Contact getContactByEmail(String email);
    Contact getContactByIdWithDetail(Long ID);
}
