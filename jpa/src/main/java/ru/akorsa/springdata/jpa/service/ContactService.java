package ru.akorsa.springdata.jpa.service;

import ru.akorsa.springdata.jpa.dto.ContactDTO;
import ru.akorsa.springdata.jpa.model.Contact;
import ru.akorsa.springdata.jpa.model.ContactPhone;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
    List<Contact> getContactsWithDetail();
    Contact add(ContactDTO added);
    Contact update(ContactDTO updated) throws NotFoundException;

    Contact findById(Long ID);
    Contact getContactByEmail(String email);
    Contact getContactByIdWithDetail(Long ID);
    Contact deleteById(Long id) throws NotFoundException;

    List<ContactPhone> findContactPhonesByContactId(Long contactId);
}
