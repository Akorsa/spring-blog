package ru.akorsa.springdata.jpa.repository;

import ru.akorsa.springdata.jpa.model.ContactEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<ContactEntity, Long> {
    List<ContactEntity> findByFirstName(String firstName);
    List<ContactEntity> findByFirstNameAndLastName(String firstName, String lastName);
    List<ContactEntity> findAllWithDetail();
}
