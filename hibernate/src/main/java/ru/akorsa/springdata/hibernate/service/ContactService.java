package ru.akorsa.springdata.hibernate.service;

import ru.akorsa.springdata.hibernate.model.Contact;

import java.util.List;

public interface ContactService {


    void createContact(Contact contact);
    List<Contact> getContacts();
    List<Contact> getContactsWithDetail();
    List<Contact> getContactsByEmail(String email);
    Contact getContact(Long id);
    void updateContact(Contact contact);
    void deleteContact(Long id);
}
