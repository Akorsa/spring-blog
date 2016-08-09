package ru.akorsa.springdata.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.akorsa.springdata.jpa.model.Contact;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
    List<Contact> findAllWithDetail();
    Contact findByEmail(String email);
    Contact findByContactIdWithDetail(Long ID);
}
