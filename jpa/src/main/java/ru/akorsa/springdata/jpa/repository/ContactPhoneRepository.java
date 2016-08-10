package ru.akorsa.springdata.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.akorsa.springdata.jpa.model.Contact;
import ru.akorsa.springdata.jpa.model.ContactPhone;

import java.util.List;

public interface ContactPhoneRepository extends CrudRepository<ContactPhone, Long> {

    ContactPhone findByContactPhoneId(Long id);
    List<ContactPhone> findByContact_ContactId(Long id);
}
