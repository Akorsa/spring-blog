package ru.akorsa.springdata.jpa.service;

import ru.akorsa.springdata.jpa.dto.ContactDTO;
import ru.akorsa.springdata.jpa.dto.HobbyDTO;
import ru.akorsa.springdata.jpa.exceptions.ContactNotFoundException;
import ru.akorsa.springdata.jpa.model.Contact;
import ru.akorsa.springdata.jpa.model.ContactPhone;
import ru.akorsa.springdata.jpa.model.Hobby;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
    List<Contact> getContactsWithDetail();
    List<Contact> searchByLastName(String lastName);

    Contact add(ContactDTO added);
    Contact update(ContactDTO updated) throws ContactNotFoundException;

    Contact findContactById(Long ID) throws ContactNotFoundException;
    Contact getContactByEmail(String email);
    Contact getContactByIdWithDetail(Long ID);
    Contact deleteById(Long id) throws ContactNotFoundException;
    Contact removeHobby(ContactDTO updated, Long hobbyId) throws ContactNotFoundException;

    List<ContactPhone> findContactPhonesByContactId(Long contactId);

    Hobby addNewHobby(HobbyDTO hobbyDTO);
    Hobby updateHobbyTitle(HobbyDTO hobbyDTO) throws ContactNotFoundException;
    List<Hobby> findAllContacts();
    Hobby findByHobbyTitle(String hobbyTitle);
}
